package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.model.DTO.ResultPaymentDTO;
import com.rootgrouptechnologies.apiUserManager.model.PeriodTime;

public interface PaymentsAnalyticService {
    ResultPaymentDTO getTotalIncomeForPeriodTime(PeriodTime periodTime);
}
