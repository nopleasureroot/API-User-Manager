package com.rootgrouptechnologies.apiUserManager.model;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import com.rootgrouptechnologies.apiUserManager.model.mapper.UserMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class LicenceDTO {
    private final String licenceKey;
    private final Boolean keyBind;
    private final String renewalDate;
}
