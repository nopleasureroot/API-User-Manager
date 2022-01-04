package com.rootgrouptechnologies.apiUserManager.service.impl;

import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.model.DTO.UserDTO;
import com.rootgrouptechnologies.apiUserManager.model.mapper.ObjectMapper;
import com.rootgrouptechnologies.apiUserManager.repository.BillingRepository;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceRepository;
import com.rootgrouptechnologies.apiUserManager.repository.UserRepository;
import com.rootgrouptechnologies.apiUserManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final DiscordServiceImpl discordService;

    private final UserRepository userRepository;
    private final LicenceRepository licenceRepository;
    private final BillingRepository billingRepository;

    @Override
    @Transactional
    public UserDTO deleteUser(Integer id)  {
        User entity = userRepository.getOne(id);

        userRepository.deleteById(id);
        licenceRepository.deleteByUserId(id);
        billingRepository.deleteByUserId(id);

        discordService.kickUserFromGuild(entity.getDiscordId().toString());

        return ObjectMapper.INSTANCE.toUserDTO(entity);
    }

    @Override
    public UserDTO updateUser(User user) {
        User entity = userRepository.getOne(user.getId());

        entity.setDiscordId(user.getDiscordId());
        userRepository.save(entity);

        return ObjectMapper.INSTANCE.toUserDTO(entity);
    }
}
