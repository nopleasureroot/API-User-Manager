package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.model.DTO.DepartedUserDTO;
import com.rootgrouptechnologies.apiUserManager.model.PeriodTime;

import java.util.List;

public interface MemberAnalyticService {
    void collectAndRecordMetrics();

    DepartedUserDTO getQuantityDepartedUsers(PeriodTime periodTime);
}
