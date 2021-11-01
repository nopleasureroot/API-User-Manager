package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import com.rootgrouptechnologies.apiUserManager.model.DTO.LicenceDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.LicenceTypeDTO;

import java.util.List;

public interface LicenceService {
    List<LicenceDTO> getLicences();

    LicenceDTO createLicence(Licence licence);

    LicenceTypeDTO changeLicenceType(Integer id, LicenceType licenceType) throws Exception;

    LicenceDTO deleteLicence(Integer id);
}
