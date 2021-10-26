package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import com.rootgrouptechnologies.apiUserManager.service.LicenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/licences")
public class LicenceController {
    private final LicenceService licenceService;

    @GetMapping("/")
    public ResponseEntity<Object> getLicences() {
        return new ResponseEntity<>(licenceService.getLicences(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> changeLicencesType(@PathVariable Integer id, @RequestBody LicenceType licenceType) throws Exception {
        return new ResponseEntity<>(licenceService.changeLicenceType(id , licenceType), HttpStatus.OK);
    }
}
