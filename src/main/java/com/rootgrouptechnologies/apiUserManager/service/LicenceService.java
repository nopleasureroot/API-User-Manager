package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import com.rootgrouptechnologies.apiUserManager.model.LicenceDTO;
import com.rootgrouptechnologies.apiUserManager.model.LicenceTypeDTO;
import com.rootgrouptechnologies.apiUserManager.model.mapper.ObjectMapper;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceRepository;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenceService {
    private final LicenceRepository licenceRepository;
    private final LicenceTypeRepository licenceTypeRepository;

    public List<LicenceDTO> getLicences() {
        List<LicenceDTO> licenceDTOS = new LinkedList<>();

        for (Licence licence : licenceRepository.findAll()) {
            licenceDTOS.add(ObjectMapper.INSTANCE.toLicenceDTO(licence));
        }

        return licenceDTOS;
    }

    public LicenceTypeDTO changeLicenceType(Integer id, LicenceType licenceType) throws Exception {
        Licence licence = licenceRepository.findLicenceByUserId(id);
        LicenceType currentLicenceType = licenceTypeRepository.findLicenceTypeById(licence.getLicenceTypeId());

        Integer newRenewal = licenceType.getRenewPrice();
        Integer oldRenewal = currentLicenceType.getRenewPrice();

        if (checkAmountPriceAndRole(newRenewal, oldRenewal, licenceType.getMajorRoleName())) {
            LicenceType newLicenceType =
                    (licenceType.getMajorRoleName().equals("Customer") && newRenewal.equals(2500))
                    ? licenceTypeRepository.findFirstByRenewPrice(newRenewal)
                    : licenceTypeRepository.findLicenceTypeByRenewPriceAndMajorRoleName(newRenewal, licenceType.getMajorRoleName());

            licence.setLicenceTypeId(newLicenceType.getId());
            if (newRenewal.equals(0)) licence.setRenewalDate("");

            licenceRepository.save(licence);

            return ObjectMapper.INSTANCE.toLicenceTypeDTO(newLicenceType);
        }

        return ObjectMapper.INSTANCE.toLicenceTypeDTO(currentLicenceType);
    }

    private boolean checkAmountPriceAndRole(Integer newRenewal, Integer oldRenewal, String roleName) throws Exception {
        if (newRenewal.equals(oldRenewal)) {
            throw new Exception("The user has a payment equal to the entered one, enter the amount different from the current one");
        }

        for (LicenceType licType : licenceTypeRepository.findAll()) {
            if (licType.getRenewPrice().equals(newRenewal) && licType.getMajorRoleName().equals(roleName)) return true;
        }

        throw new Exception("Enter the amount based on existing licenses");
    }
}
