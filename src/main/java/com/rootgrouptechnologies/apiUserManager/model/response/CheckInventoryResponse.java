package com.rootgrouptechnologies.apiUserManager.model.response;

import com.rootgrouptechnologies.apiUserManager.entity.Inventory;
import com.rootgrouptechnologies.apiUserManager.model.DTO.DropDTO;
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
    private final DropDTO inventory;
}
