package com.rootgrouptechnologies.apiUserManager.model.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PeriodTimeRequest {
    private final String startDate;
    private final String endDate;
}
