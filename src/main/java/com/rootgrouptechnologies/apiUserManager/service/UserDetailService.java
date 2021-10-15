package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.model.mapper.UserMapper;
import com.rootgrouptechnologies.apiUserManager.repository.BillingRepository;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceRepository;
import com.rootgrouptechnologies.apiUserManager.repository.LicenceTypeRepository;
import com.rootgrouptechnologies.apiUserManager.repository.UserRepository;
import com.rootgrouptechnologies.apiUserManager.entity.Billing;
import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.model.UserDTO;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailService {

    private final UserRepository userRepository;
    private final LicenceRepository licenceRepository;
    private final LicenceTypeRepository licenceTypeRepository;
    private final BillingRepository billingRepository;

    public List<UserDTO> getUsersDetails() {
        List<User> allUsers = userRepository.findAll();

        return collectUserModels(allUsers, licenceRepository, licenceTypeRepository, billingRepository);
    }

    private List<UserDTO> collectUserModels(List<User> users, LicenceRepository licenceRepository, LicenceTypeRepository licenceTypeRepository, BillingRepository billingRepository) {
        List<UserDTO> userDTO = new LinkedList<>();

        for (User user : users) {
            if (user.getDiscordUsername() != null && user.getDiscordId() != null) {
                Licence licence = licenceRepository.findLicenceByUserId(user.getId());
                Billing billing = billingRepository.findBillingByUserId(user.getId());

                if (licence != null) {
                    LicenceType licenceType = licenceTypeRepository.findLicenceTypeById(licence.getLicenceTypeId());

                    userDTO.add(UserMapper.INSTANCE.toUserDTO(user, licence, licenceType, billing));
                }
            }
        }

        return userDTO;
    }
}
