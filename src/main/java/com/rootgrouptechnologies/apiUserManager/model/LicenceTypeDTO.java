package com.rootgrouptechnologies.apiUserManager.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LicenceTypeDTO {
    private final Integer renewalPrice;
    private final String role;
}
