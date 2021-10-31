package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.model.PeriodTime;
import com.rootgrouptechnologies.apiUserManager.service.PaymentsAnalyticService;
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
    private final PaymentsAnalyticService paymentsAnalyticService;

    @PostMapping("/")
    public ResponseEntity<Object> getPaymentsForPeriodTime(@RequestBody PeriodTime periodTime) {
        return new ResponseEntity<>(paymentsAnalyticService.getTotalIncomeForPeriodTime(periodTime), HttpStatus.OK);
    }
}
