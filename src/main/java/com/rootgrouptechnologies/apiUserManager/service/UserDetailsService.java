package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.model.DTO.ResultUserDTO;

import java.util.List;

public interface UserDetailsService {
    List<ResultUserDTO> getUsersDetails();
}
