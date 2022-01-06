package com.rootgrouptechnologies.apiUserManager.model.DTO;

import lombok.Data;

@Data
public class CanceledPaymentDTO {
    private final Integer amount;
    private final String date;
}
