package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.entity.Billing;
import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.model.UserDTO;
import com.rootgrouptechnologies.apiUserManager.model.mapper.UserMapper;
import com.rootgrouptechnologies.apiUserManager.repository.BillingRepository;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceRepository;
import com.rootgrouptechnologies.apiUserManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final LicenceRepository licenceRepository;
    private final BillingRepository billingRepository;

    @Transactional
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
        licenceRepository.deleteByUserId(id);
        billingRepository.deleteByUserId(id);
    }
}
