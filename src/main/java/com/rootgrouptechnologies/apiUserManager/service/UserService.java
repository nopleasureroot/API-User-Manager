package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.entity.Billing;
import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.model.UserDTO;
import com.rootgrouptechnologies.apiUserManager.model.mapper.UserMapper;
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
    public UserDTO deleteUser(Integer id)  {
        User deletedUser = userRepository.getOne(id);
        Licence deletedLicence = licenceRepository.findLicenceByUserId(id);
        Billing deletedBilling = billingRepository.findBillingByUserId(id);

        userRepository.deleteById(id);
        licenceRepository.deleteByUserId(id);
        billingRepository.deleteByUserId(id);

        return UserMapper.INSTANCE.toUserDTO(deletedUser, deletedLicence, null, deletedBilling);
    }
}
