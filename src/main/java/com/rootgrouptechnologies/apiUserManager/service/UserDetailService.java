package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.entity.*;
import com.rootgrouptechnologies.apiUserManager.model.DTO.ResultUserDTO;
import com.rootgrouptechnologies.apiUserManager.model.mapper.ObjectMapper;
import com.rootgrouptechnologies.apiUserManager.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserDetailService {

    private final UserRepository userRepository;
    private final LicenceRepository licenceRepository;
    private final LicenceTypeRepository licenceTypeRepository;
    private final BillingRepository billingRepository;
    private final PaymentRepository paymentRepository;

    public List<ResultUserDTO> getUsersDetails() {
        List<ResultUserDTO> userDTOS = new LinkedList<>();

        for (User user : userRepository.findAll()) {
            if (user.getDiscordUsername() != null && user.getDiscordId() != null) {
                Licence licence = licenceRepository.findLicenceByUserId(user.getId());
                Billing billing = billingRepository.findBillingByUserId(user.getId());

                if (licence != null) {
                    LicenceType licenceType = licenceTypeRepository.findLicenceTypeById(licence.getLicenceTypeId());
                    Payment payment = DetailHelper.getRecentUserPayment(user.getDiscordId(), licenceType.getRenewPrice() , paymentRepository);

                    userDTOS.add(ObjectMapper.INSTANCE.toResultDTO(user, licence, licenceType, billing, payment));
                }
            }
        }

        return userDTOS;
    }

    private final static class DetailHelper {
        private static Payment getRecentUserPayment(Long discordId, Integer renewalPrice, PaymentRepository paymentRepository) {
            List<Payment> payments = paymentRepository.findAllByDiscordId(discordId);

            if (payments != null && payments.size() != 0) {
                return processPayment(payments.stream()
                        .reduce((first, second) -> second)
                        .orElse(null), renewalPrice);
            }

            return processPayment(new Payment(), renewalPrice);
        }

        private static Payment processPayment(Payment payment, Integer renewalPrice) {
            if (payment.getPaymentState() != null) {
                if (payment.getComment() != null) {
                    payment.setComment(payment.getComment().split(":")[1].trim());
                }

                return payment;
            } else {
                if (renewalPrice != 0) {
                    payment.setComment("The user only has the initial payment");
                } else {
                    payment.setComment("The user has a role that does not require payment");
                }
            }

            return payment;
        }
    }
}
