package com.rootgrouptechnologies.apiUserManager.model.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ResultPaymentDTO {
    private Integer totalIncome;
    private int qtyCanceledSub;
    private String startDate;
    private String endDate;
    private List<OneDayIncomeDTO> incomeList;
}
