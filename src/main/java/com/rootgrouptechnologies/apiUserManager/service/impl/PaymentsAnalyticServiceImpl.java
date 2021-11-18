package com.rootgrouptechnologies.apiUserManager.service.impl;

import com.rootgrouptechnologies.apiUserManager.entity.Payment;
import com.rootgrouptechnologies.apiUserManager.model.DTO.OneDayIncomeDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.ResultPaymentDTO;
import com.rootgrouptechnologies.apiUserManager.model.PeriodTime;
import com.rootgrouptechnologies.apiUserManager.repository.PaymentRepository;
import com.rootgrouptechnologies.apiUserManager.service.PaymentsAnalyticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentsAnalyticServiceImpl implements PaymentsAnalyticService {
    private final PaymentRepository paymentRepository;

    @Override
    public ResultPaymentDTO getTotalIncomeForPeriodTime(PeriodTime periodTime) {
        List<Payment> succeededPayments = paymentRepository.findByPaymentDateBetweenAndPaymentState(periodTime.getStartDate(), periodTime.getEndDate(), "succeeded");
        List<Payment> canceledPayments = paymentRepository.findByPaymentDateBetweenAndPaymentState(periodTime.getStartDate(), periodTime.getEndDate(), "canceled");

        return PaymentAnalyticHelper.processPayments(succeededPayments, canceledPayments, periodTime);
    }

    static class PaymentAnalyticHelper {
        static ResultPaymentDTO processPayments(List<Payment> succeededPayments, List<Payment> canceledPayments, PeriodTime periodTime) {
            ResultPaymentDTO resultPaymentDTO = new ResultPaymentDTO();
            List<OneDayIncomeDTO> oneDayIncomeDTOS = new LinkedList<>();

            Integer totalIncome = 0;
            int oneDayIncome = 0;
            String date;

            for (int i = 0; i < succeededPayments.size(); i++) {
                boolean compareDates = (i != succeededPayments.size() - 1)
                        ? succeededPayments.get(i+1).getPaymentDate().equals(succeededPayments.get(i).getPaymentDate())
                        : succeededPayments.get(i-1).getPaymentDate().equals(succeededPayments.get(i).getPaymentDate());

                date = succeededPayments.get(i).getPaymentDate();
                oneDayIncome += succeededPayments.get(i).getAmount();

                if (compareDates) {
                    if (i == succeededPayments.size() - 1)
                        oneDayIncomeDTOS.add(new OneDayIncomeDTO(oneDayIncome, date));
                } else {
                    oneDayIncomeDTOS.add(new OneDayIncomeDTO(oneDayIncome, date));

                    oneDayIncome = 0;
                }

                totalIncome += succeededPayments.get(i).getAmount();
            }

            resultPaymentDTO.setIncomeList(oneDayIncomeDTOS);
            resultPaymentDTO.setTotalIncome(totalIncome);
            resultPaymentDTO.setStartDate(periodTime.getStartDate());
            resultPaymentDTO.setEndDate(periodTime.getEndDate());
            resultPaymentDTO.setQtyCanceledSub(canceledPayments.size());

            return resultPaymentDTO;
        }
    }
}
