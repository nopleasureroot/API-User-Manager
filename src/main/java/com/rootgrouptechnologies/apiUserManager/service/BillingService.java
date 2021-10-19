package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.model.LicenceDTO;
import com.rootgrouptechnologies.apiUserManager.model.mapper.UserMapper;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingService {
    private final LicenceRepository licenceRepository;

    public LicenceDTO changeRenewalDate(Licence licence) {
        Licence userLicence = licenceRepository.findLicenceByUserId(licence.getId());

        userLicence.setRenewalDate(licence.getRenewalDate());

        licenceRepository.save(userLicence);

        return UserMapper.INSTANCE.toLicenceDTO(userLicence);
    }
}
