package com.rootgrouptechnologies.apiUserManager.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LicenceDTO {
    private final String licenceKey;
    private final Boolean keyBind;
    private final String renewalDate;
}
