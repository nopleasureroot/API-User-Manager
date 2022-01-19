package com.rootgrouptechnologies.apiUserManager.model.DTO;

import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class DropDTO {
    private final Integer quantity;
    private final String password;
    private final Boolean isActive;
    private final LocalDate creationDate;
}
