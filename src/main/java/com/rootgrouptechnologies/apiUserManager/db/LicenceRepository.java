package com.rootgrouptechnologies.apiUserManager.db;

import com.rootgrouptechnologies.apiUserManager.db.entity.Licence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicenceRepository extends JpaRepository<Licence, Integer> {
    Licence findLicenceByUserId(Integer userId);

}
