package com.rootgrouptechnologies.apiUserManager.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PaymentDTO {
    private final Integer amount;
    private final String comment;
    private final String paymentState;
    private final String paymentDate;
}
