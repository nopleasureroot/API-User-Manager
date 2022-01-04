package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.model.DTO.UserDTO;

public interface UserService {
    UserDTO deleteUser(Integer id);

    UserDTO updateUser(User user);
}
