package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.model.DTO.UserDTO;
import org.springframework.security.core.Authentication;

public interface UserService {
    UserDTO deleteUser(Integer id);

    UserDTO updateUser(User user);
}
