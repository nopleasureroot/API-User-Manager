package com.rootgrouptechnologies.apiUserManager.model.DTO;

import com.rootgrouptechnologies.apiUserManager.model.PeriodTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DepartedUserDTO {
    private Integer quantityDepartedUsers;
    private Integer quantityIncomeUsers;
    private Double retentionPercentage;
    private PeriodTime periodTime;
}
