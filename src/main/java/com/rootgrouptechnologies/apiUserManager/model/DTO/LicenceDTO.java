package com.rootgrouptechnologies.apiUserManager.model.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LicenceDTO {
    private final Integer id;
    private final String licenceKey;
    private final Boolean keyBind;
    private final String renewalDate;
    private final String creationDate;
}
