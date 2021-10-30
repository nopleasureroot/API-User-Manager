package com.rootgrouptechnologies.apiUserManager.repository;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenceRepository extends JpaRepository<Licence, Integer> {
    Licence findLicenceByUserId(Integer userId);
    Licence findLicenceByIdentifier(String identifier);
    Licence findLicenceById(Integer id);

    void deleteByUserId(Integer id);
}
