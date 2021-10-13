package com.rootgrouptechnologies.apiUserManager.db;

import com.rootgrouptechnologies.apiUserManager.db.entity.LicenceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenceTypeRepository extends JpaRepository<LicenceType, Integer> {
    LicenceType findLicenceTypeById(Integer id);
}
