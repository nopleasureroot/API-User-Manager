package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/billings")
public class BillingController {
    private final BillingService billingService;

    @PutMapping("/")
    public ResponseEntity<Object> changeRenewalDate(@RequestBody Licence licence) {
        return new ResponseEntity<>(billingService.changeRenewalDate(licence), HttpStatus.OK);
    }
}
