package com.rootgrouptechnologies.apiUserManager.service.impl;

import com.rootgrouptechnologies.apiUserManager.entity.Inventory;
import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.entity.Payment;
import com.rootgrouptechnologies.apiUserManager.model.DTO.DropDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.LicenceDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.PaymentDTO;
import com.rootgrouptechnologies.apiUserManager.model.mapper.ObjectMapper;
import com.rootgrouptechnologies.apiUserManager.model.request.DropRequest;
import com.rootgrouptechnologies.apiUserManager.model.response.CheckInventoryResponse;
import com.rootgrouptechnologies.apiUserManager.repository.InventoryRepository;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceRepository;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceTypeRepository;
import com.rootgrouptechnologies.apiUserManager.repository.PaymentRepository;
import com.rootgrouptechnologies.apiUserManager.service.DropService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class DropServiceImpl implements DropService {
    private final InventoryRepository inventoryRepository;
    private final LicenceTypeRepository licenceTypeRepository;
    private final LicenceRepository licenceRepository;
    private final PaymentRepository paymentRepository;

    private String infoMessage = "";
    private Boolean dropIsActive;

    @Override
    @Transactional
    public DropDTO createDrop(DropRequest dropRequest) throws Exception {
        if (licenceTypeRepository.findLicenceTypeById(dropRequest.getLicenceType().getId()) != null
                && inventoryRepository.findInventoryByPasswordAndQuantityAndLicenceTypeId(dropRequest.getPassword(), dropRequest.getQuantity(), dropRequest.getLicenceType().getId()) == null
                && dropRequest.getPassword() != null && !dropRequest.getPassword().isEmpty()
                && dropRequest.getQuantity() >= 1) {
            dropIsActive = true;

            Inventory inventory = new Inventory();

            inventory.setPassword(dropRequest.getPassword());
            inventory.setQuantity(dropRequest.getQuantity());
            inventory.setLicenceTypeId(dropRequest.getLicenceType().getId());
            inventory.setCreationDate(dropRequest.getCreationDate());
            inventory.setAutoRestock(dropRequest.getAutoRestock());
            inventory.setDeleteAfterDrop(dropRequest.getDeleteAfterSoldOut());
            inventory.setIsActive(true);
            inventory.setInitialQty(dropRequest.getQuantity());

            inventoryRepository.save(inventory);

            CompletableFuture.runAsync(() -> {
                try {
                    scheduledCheckInventory(dropRequest.getPassword());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            return new DropDTO(dropRequest.getQuantity(), dropRequest.getPassword(), licenceTypeRepository.findLicenceTypeById(dropRequest.getLicenceType().getId()));
        }

        throw new Exception("Request body must be valid");
    }

    @Override
    public DropDTO deleteDrop(String password) throws Exception {
        Inventory inventory = inventoryRepository.findInventoryByPassword(password);

        if (inventory != null) {
            inventoryRepository.delete(inventory);

            return new DropDTO(inventory.getQuantity(), inventory.getPassword(), licenceTypeRepository.findLicenceTypeById(inventory.getLicenceTypeId()));
        }

        throw new Exception("Some error occur delete drop");
    }

    @Override
    @Transactional
    public void scheduledCheckInventory(String password) throws InterruptedException {
        while (dropIsActive) {
            TimeUnit.MILLISECONDS.sleep(500);

            Inventory inventory = inventoryRepository.findInventoryByPassword(password);
            List<Licence> licences = licenceRepository.findLicencesByCreationDateGreaterThanEqualAndCreationDateLessThan(inventory.getCreationDate().toString(), inventory.getCreationDate().plusDays(1).toString());

            Integer canceledPayments = DropHelper.calculateCanceledPayments(inventory, licences);

            if (canceledPayments > 0 && inventory.getAutoRestock() && inventory.getQuantity() != 0) {
                inventory.setQuantity(inventory.getQuantity() + canceledPayments);
                inventoryRepository.save(inventory);

                infoMessage = "Restocking was carried out for " + canceledPayments + " keys";
            }

            if (inventory.getQuantity() <= 0 && inventory.getDeleteAfterDrop()) {
                inventory.setIsActive(false);
                inventoryRepository.save(inventory);

                dropIsActive = false;

                infoMessage = "The keys were sold out successfully and the drop was deleted";
                return;
            }
        }
    }

    @Override
    @Transactional
    public CheckInventoryResponse checkInventory(String password) {
        String message = infoMessage;
        if (message.contains("deleted")) infoMessage = "";

        Inventory inventory = inventoryRepository.findInventoryByPassword(password);

        List<Payment> payments = paymentRepository.findPaymentsByPaymentDateGreaterThanEqualAndPaymentDateLessThan(inventory.getCreationDate().toString(), inventory.getCreationDate().plusDays(1).toString());
        List<Licence> licences = licenceRepository.findLicencesByCreationDateGreaterThanEqualAndCreationDateLessThan(inventory.getCreationDate().toString(), inventory.getCreationDate().plusDays(1).toString());

        Integer canceledPayments = DropHelper.calculateCanceledPayments(inventory, licences);

        return new CheckInventoryResponse(DropHelper.convertToDTO(licences), DropHelper.convertToDTO(payments), canceledPayments, message, inventory.getIsActive());
    }

    static class DropHelper {
        static private List<?> convertToDTO(List<?> objects) {
            List<LicenceDTO> licenceDTOS = new LinkedList<>();
            List<PaymentDTO> paymentDTOS = new LinkedList<>();

            for (Object object : objects) {
                if (object instanceof Licence) {
                    assert licenceDTOS != null;
                    licenceDTOS.add(ObjectMapper.INSTANCE.toLicenceDTO((Licence) object));

                    paymentDTOS = null;
                } else if (object instanceof Payment) {
                    assert paymentDTOS != null;
                    paymentDTOS.add(ObjectMapper.INSTANCE.toPaymentDTO((Payment) object));

                    licenceDTOS = null;
                }
            }

            return (licenceDTOS != null) ?
                    licenceDTOS :
                    paymentDTOS;
        }

        static private Integer calculateCanceledPayments(Inventory inventory, List<Licence> licences) {
            int canceledPayments = 0;

            int quantityPaidLicences = licences.size();
            int quantityKeys = inventory.getQuantity();

            int differenceWithInitialQty = inventory.getInitialQty() - quantityPaidLicences;

            if (quantityKeys != differenceWithInitialQty) {
                canceledPayments = Math.max(differenceWithInitialQty - quantityKeys, 0);
            }

            return canceledPayments;
        }
    }
}
