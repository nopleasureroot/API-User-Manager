package com.rootgrouptechnologies.apiUserManager.model;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private final Integer id;
    private final String discordUsername;
    private final String discordEmail;
    private final LicenceDTO licenceDTO;
    private final BillingDTO billingDTO;
    private final LicenceTypeDTO licenceTypeDTO;
//    private final String creationDate;
//    private final String discordId;
//    private final String discordAvatar;
//    private final String role;
//    private final String licenceKey;
//    private final Boolean keyBind;
//    private final Date renewDate;
//    private final Integer renewPrice;
//    private final Boolean cartBind;
//    private final Integer cartEnding;
//    private final String cartDate;
//    private final String paymentId;
}
