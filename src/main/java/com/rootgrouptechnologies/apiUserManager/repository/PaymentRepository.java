package com.rootgrouptechnologies.apiUserManager.repository;

import com.rootgrouptechnologies.apiUserManager.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAllByDiscordId(Long discordId);

    List<Payment> findByPaymentDateBetweenAndPaymentState(String startDate, String endDate, String paymentState);
    List<Payment> findPaymentsByPaymentDateGreaterThanEqualAndPaymentDateLessThan(String startDate, String endDate);
}
