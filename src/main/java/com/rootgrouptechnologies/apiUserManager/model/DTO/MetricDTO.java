package com.rootgrouptechnologies.apiUserManager.model.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MetricDTO {
    private String date;
    private int departedUsers;
}
