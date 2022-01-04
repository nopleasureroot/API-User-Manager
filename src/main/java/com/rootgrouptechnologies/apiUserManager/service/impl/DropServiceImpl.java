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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DropServiceImpl implements DropService {
    private final InventoryRepository inventoryRepository;
    private final LicenceTypeRepository licenceTypeRepository;
    private final LicenceRepository licenceRepository;
    private final PaymentRepository paymentRepository;

    private boolean deleteAfterSoldOut;
    private int initialQty;
    private boolean autoRestockSlots;
    private boolean mustBind;
    private LocalDate creationDate;

    private void initDropParams(DropRequest dropRequest) {
        initialQty = dropRequest.getQuantity();
        deleteAfterSoldOut = dropRequest.getDeleteAfterSoldOut();
        autoRestockSlots = dropRequest.getAutoRestock();
        mustBind = dropRequest.getMustBind();
        creationDate = dropRequest.getCreationDate();
    }

    @Override
    @Transactional
    public DropDTO createDrop(DropRequest dropRequest) throws Exception {
        if (licenceTypeRepository.findLicenceTypeById(dropRequest.getLicenceType().getId()) != null
                && inventoryRepository.findInventoryByPasswordAndQuantityAndLicenceTypeId(dropRequest.getPassword(), dropRequest.getQuantity(), dropRequest.getLicenceType().getId()) == null
                && dropRequest.getPassword() != null && !dropRequest.getPassword().isEmpty()
                && dropRequest.getQuantity() >= 1) {
            initDropParams(dropRequest);

            Inventory inventory = new Inventory();

            inventory.setPassword(dropRequest.getPassword());
            inventory.setQuantity(dropRequest.getQuantity());
            inventory.setLicenceTypeId(dropRequest.getLicenceType().getId());

            inventoryRepository.save(inventory);

            return new DropDTO(dropRequest.getQuantity(), dropRequest.getPassword(), licenceTypeRepository.findLicenceTypeById(dropRequest.getLicenceType().getId()));
        }

        throw new Exception("Request body must be valid");
    }

    @Override
    @Transactional
    public CheckInventoryResponse checkInventory(String password) {
        Inventory inventory = inventoryRepository.findInventoryByPassword(password);

        List<Payment> payments = paymentRepository.findPaymentsByPaymentDateGreaterThanEqualAndPaymentDateLessThan(creationDate.toString(), creationDate.plusDays(1).toString());
        List<Licence> licences = licenceRepository.findLicencesByCreationDateGreaterThanEqualAndCreationDateLessThan(creationDate.toString(), creationDate.plusDays(1).toString());

        Integer canceledPayments = calculateCanceledPayments(inventory, licences);

        if (canceledPayments > 0 && autoRestockSlots && inventory.getQuantity() != 0) {
            inventory.setQuantity(inventory.getQuantity() + canceledPayments);
            inventoryRepository.save(inventory);

            return new CheckInventoryResponse(convertToDTO(licences), convertToDTO(payments), canceledPayments, "Restocking was carried out for " + canceledPayments + " keys");
        }

        if (inventory.getQuantity() <= 0 && deleteAfterSoldOut) {
            inventoryRepository.delete(inventory);

            return new CheckInventoryResponse(convertToDTO(licences), convertToDTO(payments), canceledPayments,"The keys were sold out successfully and the drop was deleted");
        }

        return new CheckInventoryResponse(convertToDTO(licences), convertToDTO(payments), canceledPayments,"Processing...");
    }

    private List<?> convertToDTO(List<?> objects) {
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

    private Integer calculateCanceledPayments(Inventory inventory, List<Licence> licences) {
        int canceledPayments = 0;

        int quantityPaidLicences = licences.size();
        int quantityKeys = inventory.getQuantity();

        int differenceWithInitialQty = initialQty - quantityPaidLicences;

        if (quantityKeys != differenceWithInitialQty) {
            canceledPayments = Math.max(differenceWithInitialQty - quantityKeys, 0);
        }

        return canceledPayments;
    }
}
