package com.rootgrouptechnologies.apiUserManager.repository;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface LicenceRepository extends JpaRepository<Licence, Integer> {
    Licence findLicenceByUserId(Integer userId);
    Licence findLicenceByIdentifier(String identifier);
    Licence findLicenceById(Integer id);

    List<Licence> findLicencesByCreationDateGreaterThanEqualAndCreationDateLessThan(String date, String date2);

    void deleteByUserId(Integer id);
}
