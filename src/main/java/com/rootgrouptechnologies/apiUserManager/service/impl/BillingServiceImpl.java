package com.rootgrouptechnologies.apiUserManager.service.impl;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.model.DTO.LicenceDTO;
import com.rootgrouptechnologies.apiUserManager.model.mapper.ObjectMapper;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceRepository;
import com.rootgrouptechnologies.apiUserManager.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {
    private final LicenceRepository licenceRepository;

    @Override
    public LicenceDTO changeRenewalDate(Licence licence) {
        Licence userLicence = licenceRepository.findLicenceByUserId(licence.getUserId());

        userLicence.setRenewalDate(licence.getRenewalDate());

        licenceRepository.save(userLicence);

        return ObjectMapper.INSTANCE.toLicenceDTO(userLicence);
    }
}
