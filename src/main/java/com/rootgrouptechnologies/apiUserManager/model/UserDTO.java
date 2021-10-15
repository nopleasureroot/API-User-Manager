package com.rootgrouptechnologies.apiUserManager.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private final Integer id;
    private final String discordUsername;
    private final String discordEmail;
    private final String creationDate;
    private final LicenceDTO licenceDTO;
    private final BillingDTO billingDTO;
    private final LicenceTypeDTO licenceTypeDTO;
}
