package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.model.DTO.DepartedUserDTO;
import com.rootgrouptechnologies.apiUserManager.model.request.PeriodTimeRequest;

public interface MemberAnalyticService {
    void collectAndRecordMetrics();

    DepartedUserDTO getQuantityDepartedUsers(PeriodTimeRequest periodTimeRequest);
}
