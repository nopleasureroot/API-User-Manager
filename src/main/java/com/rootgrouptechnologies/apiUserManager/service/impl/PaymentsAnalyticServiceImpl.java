package com.rootgrouptechnologies.apiUserManager.service.impl;

import com.rootgrouptechnologies.apiUserManager.entity.Payment;
import com.rootgrouptechnologies.apiUserManager.model.DTO.ResultPaymentDTO;
import com.rootgrouptechnologies.apiUserManager.model.PeriodTime;
import com.rootgrouptechnologies.apiUserManager.repository.PaymentRepository;
import com.rootgrouptechnologies.apiUserManager.service.PaymentsAnalyticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentsAnalyticServiceImpl implements PaymentsAnalyticService {
    private final PaymentRepository paymentRepository;

    @Override
    public ResultPaymentDTO getTotalIncomeForPeriodTime(PeriodTime periodTime) {
        List<Payment> succeededPayments = paymentRepository.findByPaymentDateBetweenAndPaymentState(periodTime.getStartDate(), periodTime.getEndDate(), "succeeded");
        List<Payment> canceledPayments = paymentRepository.findByPaymentDateBetweenAndPaymentState(periodTime.getStartDate(), periodTime.getEndDate(), "canceled");

        return processPayments(succeededPayments, canceledPayments, periodTime);
    }

    private ResultPaymentDTO processPayments(List<Payment> succeededPayments, List<Payment> canceledPayments, PeriodTime periodTime) {
        ResultPaymentDTO resultPaymentDTO = new ResultPaymentDTO();
        Integer totalIncome = 0;

        for (Payment succeededPayment : succeededPayments) {
            totalIncome += succeededPayment.getAmount();
        }

        resultPaymentDTO.setTotalIncome(totalIncome);
        resultPaymentDTO.setStartDate(periodTime.getStartDate());
        resultPaymentDTO.setEndDate(periodTime.getEndDate());
        resultPaymentDTO.setQtyCanceledSub(canceledPayments.size());

        return resultPaymentDTO;
    }
}
