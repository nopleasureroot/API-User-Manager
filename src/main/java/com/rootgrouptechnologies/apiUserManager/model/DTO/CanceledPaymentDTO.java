package com.rootgrouptechnologies.apiUserManager.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CanceledPaymentDTO {
    private Integer qty;
    private String date;
}
