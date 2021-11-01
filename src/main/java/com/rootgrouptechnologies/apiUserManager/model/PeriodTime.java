package com.rootgrouptechnologies.apiUserManager.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PeriodTime {
    private final String startDate;
    private final String endDate;
}
