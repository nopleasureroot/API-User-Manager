package com.rootgrouptechnologies.apiUserManager.service.impl;

import com.rootgrouptechnologies.apiUserManager.entity.Payment;
import com.rootgrouptechnologies.apiUserManager.model.DTO.OneDayIncomeDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.ResultPaymentDTO;
import com.rootgrouptechnologies.apiUserManager.model.request.PeriodTimeRequest;
import com.rootgrouptechnologies.apiUserManager.repository.PaymentRepository;
import com.rootgrouptechnologies.apiUserManager.service.PaymentsAnalyticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentsAnalyticServiceImpl implements PaymentsAnalyticService {
    private final PaymentRepository paymentRepository;

    @Override
    public ResultPaymentDTO getTotalIncomeForPeriodTime(PeriodTimeRequest periodTimeRequest) {
        List<Payment> succeededPayments = paymentRepository.findByPaymentDateBetweenAndPaymentState(periodTimeRequest.getStartDate(), periodTimeRequest.getEndDate(), "succeeded");
        List<Payment> canceledPayments = paymentRepository
                .findByPaymentDateBetweenAndPaymentState(periodTimeRequest.getStartDate(), periodTimeRequest.getEndDate(), "canceled")
                .stream()
                .sorted((Comparator.comparing(Payment::getPaymentDate)))
                .collect(Collectors.toList());

        return PaymentAnalyticHelper.processPayments(succeededPayments, canceledPayments, periodTimeRequest);
    }

    static class PaymentAnalyticHelper {
        static ResultPaymentDTO processPayments(List<Payment> succeededPayments, List<Payment> canceledPayments, PeriodTimeRequest periodTimeRequest) {
            ResultPaymentDTO resultPaymentDTO = new ResultPaymentDTO();
            List<OneDayIncomeDTO> oneDayIncomeDTOS = new LinkedList<>();

            Integer totalIncome = 0;
            int oneDayIncome = 0;
            String date;

            for (int i = 0; i < succeededPayments.size(); i++) {
                boolean compareDates;

                if (succeededPayments.size() != 1) {
                    compareDates = (i != succeededPayments.size() - 1)
                            ? succeededPayments.get(i + 1).getPaymentDate().equals(succeededPayments.get(i).getPaymentDate())
                            : succeededPayments.get(i - 1).getPaymentDate().equals(succeededPayments.get(i).getPaymentDate());

                } else {
                    compareDates = true;
                }


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
            resultPaymentDTO.setStartDate(periodTimeRequest.getStartDate());
            resultPaymentDTO.setEndDate(periodTimeRequest.getEndDate());
            resultPaymentDTO.setQtyCanceledSub(canceledPayments.size());

            return resultPaymentDTO;
        }
    }
}
