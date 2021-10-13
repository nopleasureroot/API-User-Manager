package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.db.BillingRepository;
import com.rootgrouptechnologies.apiUserManager.db.LicenceRepository;
import com.rootgrouptechnologies.apiUserManager.db.LicenceTypeRepository;
import com.rootgrouptechnologies.apiUserManager.db.UserRepository;
import com.rootgrouptechnologies.apiUserManager.entity.Billing;
import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.model.CollectedUser;
import com.rootgrouptechnologies.apiUserManager.utils.ClassUtils;

import lombok.RequiredArgsConstructor;
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

    public List<CollectedUser> getUsersDetails() {
        List<User> allUsers = userRepository.findAll();

        return collectUserModels(allUsers, licenceRepository, licenceTypeRepository, billingRepository);
    }

    private List<CollectedUser> collectUserModels(List<User> users, LicenceRepository licenceRepository, LicenceTypeRepository licenceTypeRepository, BillingRepository billingRepository) {
        List<CollectedUser> collectedUsers = new LinkedList<>();

        for (User user : users) {
            if (user.getDiscordUsername() != null && user.getDiscordId() != null) {
                Licence licence = licenceRepository.findLicenceByUserId(user.getId());
                Billing billing = billingRepository.findBillingByUserId(user.getId());

                if (licence != null) {
                    LicenceType licenceType = licenceTypeRepository.findLicenceTypeById(licence.getLicenceTypeId());

                    collectedUsers.add(new CollectedUser(ClassUtils.convertData(user, licence, licenceType, billing)));
                }
            }
        }

        return collectedUsers;
    }
}