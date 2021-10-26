package com.rootgrouptechnologies.apiUserManager.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResultDTO {
    private final UserDTO userDTO;
    private final LicenceDTO licenceDTO;
    private final LicenceTypeDTO licenceTypeDTO;
    private final BillingDTO billingDTO;
}
