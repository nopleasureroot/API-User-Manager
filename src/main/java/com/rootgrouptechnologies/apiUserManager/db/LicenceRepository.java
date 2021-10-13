package com.rootgrouptechnologies.apiUserManager.db;

import com.rootgrouptechnologies.apiUserManager.entity.Licence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenceRepository extends JpaRepository<Licence, Integer> {
    Licence findLicenceByUserId(Integer userId);
}
