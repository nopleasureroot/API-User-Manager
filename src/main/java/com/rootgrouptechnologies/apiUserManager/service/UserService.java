package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.repository.BillingRepository;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceRepository;
import com.rootgrouptechnologies.apiUserManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final LicenceRepository licenceRepository;
    private final BillingRepository billingRepository;

    @Transactional
    public String deleteUser(Integer id) {
        User deletedUser = userRepository.getOne(id);

        userRepository.deleteById(id);
        licenceRepository.deleteByUserId(id);
        billingRepository.deleteByUserId(id);

        return deletedUser.getDiscordUsername() + " has been successfully deleted";
    }
}
