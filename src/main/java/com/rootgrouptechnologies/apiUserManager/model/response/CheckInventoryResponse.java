package com.rootgrouptechnologies.apiUserManager.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CheckInventoryResponse {
    private final List<?> licences;
    private final List<?> payments;
    private final Integer canceledPayments;
    private final String message;
    private final Boolean status;
}
