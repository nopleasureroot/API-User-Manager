package com.rootgrouptechnologies.apiUserManager.db;

import com.rootgrouptechnologies.apiUserManager.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Integer> {
    Billing findBillingByUserId(Integer id);
}
