package com.rootgrouptechnologies.apiUserManager.model.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResultPaymentDTO {
    private Integer totalIncome;
    private int qtyCanceledSub;
    private String startDate;
    private String endDate;
}
