package com.rootgrouptechnologies.apiUserManager.model.DTO;

import com.rootgrouptechnologies.apiUserManager.model.request.PeriodTimeRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class DepartedUserDTO {
    private Integer quantityDepartedUsers;
    private Integer quantityIncomeUsers;
    private Double retentionPercentage;
    private List<MetricDTO> listMetrics;
    private PeriodTimeRequest periodTimeRequest;
}
