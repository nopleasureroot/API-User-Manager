package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.model.UserDTO;
import com.rootgrouptechnologies.apiUserManager.model.mapper.ObjectMapper;
import com.rootgrouptechnologies.apiUserManager.repository.BillingRepository;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceRepository;
import com.rootgrouptechnologies.apiUserManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final LicenceRepository licenceRepository;
    private final BillingRepository billingRepository;

    @Transactional
    public UserDTO deleteUser(Integer id)  {
        User entity = userRepository.getOne(id);

        userRepository.deleteById(id);
        licenceRepository.deleteByUserId(id);
        billingRepository.deleteByUserId(id);

        return ObjectMapper.INSTANCE.toUserDTO(entity);
    }

    public UserDTO updateUser(User user) {
        User entity = userRepository.getOne(user.getId());

        entity.setDiscordId(user.getDiscordId());
        userRepository.save(entity);

        return ObjectMapper.INSTANCE.toUserDTO(entity);
    }
}
