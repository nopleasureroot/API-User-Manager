package com.rootgrouptechnologies.apiUserManager.service;

import com.rootgrouptechnologies.apiUserManager.entity.Payment;
import com.rootgrouptechnologies.apiUserManager.model.DTO.ResultPaymentDTO;
import com.rootgrouptechnologies.apiUserManager.model.PeriodTime;
import com.rootgrouptechnologies.apiUserManager.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentsAnalyticService {
    private final PaymentRepository paymentRepository;

    public ResultPaymentDTO getTotalIncomeForPeriodTime(PeriodTime periodTime) {
        List<Payment> succeededPayments = paymentRepository.findByPaymentDateAfterAndPaymentDateBeforeAndPaymentStateEquals(periodTime.getIntervalStart(), periodTime.getIntervalEnd(), "succeeded");
        List<Payment> canceledPayments = paymentRepository.findByPaymentDateAfterAndPaymentDateBeforeAndPaymentStateEquals(periodTime.getIntervalStart(), periodTime.getIntervalEnd(), "canceled");

        return processPayments(succeededPayments, canceledPayments, periodTime);
    }

    private ResultPaymentDTO processPayments(List<Payment> succeededPayments, List<Payment> canceledPayments, PeriodTime periodTime) {
        ResultPaymentDTO resultPaymentDTO = new ResultPaymentDTO();
        Integer totalIncome = 0;

        for (Payment succeededPayment : succeededPayments) {
            totalIncome += succeededPayment.getAmount();
        }

        resultPaymentDTO.setTotalIncome(totalIncome);
        resultPaymentDTO.setStartDate(periodTime.getIntervalStart());
        resultPaymentDTO.setEndDate(periodTime.getIntervalEnd());
        resultPaymentDTO.setQtyCanceledSub(canceledPayments.size());

        return resultPaymentDTO;
    }
}
