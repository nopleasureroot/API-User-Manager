package com.rootgrouptechnologies.apiUserManager.repository;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenceRepository extends JpaRepository<Licence, Integer> {
    Licence findLicenceByUserId(Integer userId);
    void deleteByUserId(Integer id);
    Licence findLicenceByIdentifier(String identifier);
}
