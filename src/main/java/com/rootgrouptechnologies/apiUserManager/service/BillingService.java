package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.model.DTO.LicenceDTO;

public interface BillingService {
    LicenceDTO changeRenewalDate(Licence licence);
}
