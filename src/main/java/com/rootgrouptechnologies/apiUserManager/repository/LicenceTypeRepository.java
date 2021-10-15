package com.rootgrouptechnologies.apiUserManager.repository;

import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenceTypeRepository extends JpaRepository<LicenceType, Integer> {
    LicenceType findLicenceTypeById(Integer id);
}
