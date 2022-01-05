package com.rootgrouptechnologies.apiUserManager.model.request;

import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class DropRequest {
    private final String password;
    private final Integer quantity;
    private final LicenceType licenceType;
    private final Boolean autoRestock;
    private final Boolean deleteAfterSoldOut;
    private final Boolean mustBind;
    private final LocalDate creationDate;
}
