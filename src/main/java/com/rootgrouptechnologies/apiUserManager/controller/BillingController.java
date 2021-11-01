package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.service.impl.BillingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/billings")
public class BillingController {
    private final BillingServiceImpl billingServiceImpl;

    @PutMapping("/")
    public ResponseEntity<Object> changeRenewalDate(@RequestBody Licence licence) {
        return new ResponseEntity<>(billingServiceImpl.changeRenewalDate(licence), HttpStatus.OK);
    }
}
