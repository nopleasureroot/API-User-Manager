package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.service.BillingService;
import com.rootgrouptechnologies.apiUserManager.utils.ClassUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/billing")
public class BillingController {
    private final BillingService billingService;

    @PutMapping("/")
    public ResponseEntity<Object> updateRenewalDate(@RequestBody Licence licence) {
        return new ResponseEntity<>(billingService.changeRenewalDate(licence), ClassUtils.configureResponseHeaders(), HttpStatus.OK);
    }
}
