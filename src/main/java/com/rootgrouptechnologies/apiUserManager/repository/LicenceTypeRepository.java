package com.rootgrouptechnologies.apiUserManager.repository;

import com.rootgrouptechnologies.apiUserManager.entity.LicenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenceTypeRepository extends JpaRepository<LicenceType, Integer> {
    LicenceType findLicenceTypeById(Integer id);
    LicenceType findLicenceTypeByRenewPriceAndMajorRoleName(Integer renewPrice, String name);
    LicenceType findFirstByRenewPrice(Integer renewPrice);
}
