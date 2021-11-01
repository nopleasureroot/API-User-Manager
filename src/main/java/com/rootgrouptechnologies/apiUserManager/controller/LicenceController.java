package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import com.rootgrouptechnologies.apiUserManager.service.impl.LicenceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/licences")
public class LicenceController {
    private final LicenceServiceImpl licenceServiceImpl;

    @GetMapping("/")
    public ResponseEntity<Object> getLicences() {
        return new ResponseEntity<>(licenceServiceImpl.getLicences(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createLicence(@RequestBody Licence licence) {
        return new ResponseEntity<>(licenceServiceImpl.createLicence(licence), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> changeLicenceType(@PathVariable Integer id, @RequestBody LicenceType licenceType) throws Exception {
        return new ResponseEntity<>(licenceServiceImpl.changeLicenceType(id , licenceType), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLicence(@PathVariable Integer id) {
        return new ResponseEntity<>(licenceServiceImpl.deleteLicence(id), HttpStatus.ACCEPTED);
    }
}
