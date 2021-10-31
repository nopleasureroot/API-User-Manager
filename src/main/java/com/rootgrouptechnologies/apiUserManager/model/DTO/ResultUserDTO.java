package com.rootgrouptechnologies.apiUserManager.model.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResultUserDTO {
    private final UserDTO userDTO;
    private final LicenceDTO licenceDTO;
    private final LicenceTypeDTO licenceTypeDTO;
    private final BillingDTO billingDTO;
    private final PaymentDTO paymentDTO;
}
