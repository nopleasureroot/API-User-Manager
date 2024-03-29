package com.rootgrouptechnologies.apiUserManager.service.impl;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import com.rootgrouptechnologies.apiUserManager.model.DTO.LicenceDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.LicenceTypeDTO;
import com.rootgrouptechnologies.apiUserManager.model.mapper.ObjectMapper;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceRepository;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceTypeRepository;
import com.rootgrouptechnologies.apiUserManager.service.LicenceService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LicenceServiceImpl implements LicenceService {
    private final LicenceRepository licenceRepository;
    private final LicenceTypeRepository licenceTypeRepository;

    @Override
    public List<LicenceDTO> getLicences() {
        List<LicenceDTO> licenceDTOS = new LinkedList<>();

        for (Licence licence : licenceRepository.findAll()) {
            licenceDTOS.add(ObjectMapper.INSTANCE.toLicenceDTO(licence));
        }

        return licenceDTOS.subList(licenceDTOS.size() - 15, licenceDTOS.size());
    }

    @Override
    public LicenceDTO createLicence(Licence licence) {
        String formattedDate = LicenceHelper.getFormattedNowDate();
        String licenceToken = LicenceHelper.generateLicenceToken(licenceRepository);
        String renewalDate = licence.getRenewalDate();
        Integer licenceTypeId = licence.getLicenceTypeId();

        Licence newLicence = LicenceHelper.collectNewLicence(licenceToken, renewalDate, licenceTypeId, formattedDate);
        licenceRepository.save(newLicence);

        return ObjectMapper.INSTANCE.toLicenceDTO(newLicence);
    }

    @Override
    public LicenceTypeDTO changeLicenceType(Integer id, LicenceType licenceType) throws Exception {
        Licence licence = licenceRepository.findLicenceByUserId(id);
        LicenceType currentLicenceType = licenceTypeRepository.findLicenceTypeById(licence.getLicenceTypeId());

        Integer newRenewal = licenceType.getRenewPrice();
        Integer oldRenewal = currentLicenceType.getRenewPrice();

        if (LicenceHelper.checkAmountPriceAndRole(newRenewal, oldRenewal, licenceType.getMajorRoleName(), licenceTypeRepository)) {
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

    @Override
    public LicenceDTO deleteLicence(Integer id) {
        Licence entity = licenceRepository.findLicenceById(id);

        licenceRepository.deleteById(id);

        return ObjectMapper.INSTANCE.toLicenceDTO(entity);
    }


    static class LicenceHelper {
        private static boolean checkAmountPriceAndRole(Integer newRenewal, Integer oldRenewal, String roleName, LicenceTypeRepository licenceTypeRepository) throws Exception {
            if (newRenewal.equals(oldRenewal)) {
                throw new Exception("The user has a payment equal to the entered one, enter the amount different from the current one");
            }

            for (LicenceType licType : licenceTypeRepository.findAll()) {
                if (licType.getRenewPrice().equals(newRenewal) && licType.getMajorRoleName().equals(roleName))
                    return true;
            }

            throw new Exception("Enter the amount based on existing licenses");
        }

        private static String getFormattedNowDate() {
            return LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        }

        private static String generateLicenceToken(LicenceRepository licenceRepository) {
            RandomStringGenerator randomStringGenerator = new RandomStringGenerator
                    .Builder()
                    .withinRange('A', 'z')
                    .filteredBy(CharacterPredicates.LETTERS)
                    .build();

            String licenceToken = randomStringGenerator.generate(20).toUpperCase(Locale.ROOT);

            if (licenceRepository.findLicenceByIdentifier(licenceToken) != null) return generateLicenceToken(licenceRepository);

            return licenceToken;
        }

        private static Licence collectNewLicence(String licenceToken, String renewalDate, Integer licenceTypeId, String formattedDate) {
            Licence licence = new Licence();

            licence.setCreationDate(formattedDate);
            licence.setLicenceTypeId(licenceTypeId);
            licence.setRenewalDate(renewalDate);
            licence.setActivated(false);
            licence.setIdentifier(licenceToken);
            licence.setUserId(null);

            return licence;
        }
    }
}
