package com.rootgrouptechnologies.apiUserManager.db;

import com.rootgrouptechnologies.apiUserManager.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
