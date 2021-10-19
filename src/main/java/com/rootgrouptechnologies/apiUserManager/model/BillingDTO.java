package com.rootgrouptechnologies.apiUserManager.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BillingDTO {
    private final String cartDate;
    private final Integer cartEnding;
    private final String paymentId;
    private final Boolean cartBind;
}
