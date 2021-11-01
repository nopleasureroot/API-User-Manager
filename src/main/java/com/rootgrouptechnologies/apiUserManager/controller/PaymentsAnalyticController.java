package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.model.PeriodTime;
import com.rootgrouptechnologies.apiUserManager.service.impl.PaymentsAnalyticServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentsAnalyticController {
    private final PaymentsAnalyticServiceImpl paymentsAnalyticServiceImpl;

    @PostMapping("/")
    public ResponseEntity<Object> getTotalIncomeForPeriodTime(@RequestBody PeriodTime periodTime) {
        return new ResponseEntity<>(paymentsAnalyticServiceImpl.getTotalIncomeForPeriodTime(periodTime), HttpStatus.OK);
    }
}
