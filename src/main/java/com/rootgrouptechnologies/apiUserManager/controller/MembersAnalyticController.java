package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.model.request.PeriodTimeRequest;
import com.rootgrouptechnologies.apiUserManager.service.impl.MemberAnalyticServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MembersAnalyticController {
    private final MemberAnalyticServiceImpl memberAnalyticService;

    @PostMapping("/retention")
    public ResponseEntity<Object> getDepartedUsers(@RequestBody PeriodTimeRequest periodTimeRequest) {
        return new ResponseEntity<>(memberAnalyticService.getQuantityDepartedUsers(periodTimeRequest), HttpStatus.OK);
    }
}
