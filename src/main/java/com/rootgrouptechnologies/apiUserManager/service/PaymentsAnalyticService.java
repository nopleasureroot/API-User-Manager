package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.model.DTO.ResultPaymentDTO;
import com.rootgrouptechnologies.apiUserManager.model.request.PeriodTimeRequest;

public interface PaymentsAnalyticService {
    ResultPaymentDTO getTotalIncomeForPeriodTime(PeriodTimeRequest periodTimeRequest);
}
