package com.rootgrouptechnologies.apiUserManager.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OneDayIncomeDTO {
    private Integer oneDayIncome;
    private String date;
    private Integer qty;
}
