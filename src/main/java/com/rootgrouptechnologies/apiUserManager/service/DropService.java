package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.model.DTO.DropDTO;
import com.rootgrouptechnologies.apiUserManager.model.request.DropRequest;
import com.rootgrouptechnologies.apiUserManager.model.response.CheckInventoryResponse;

public interface DropService {
    DropDTO createDrop(DropRequest dropRequest) throws Exception;

    CheckInventoryResponse checkInventory(String password);

    void scheduledCheckInventory(String password) throws InterruptedException;
}
