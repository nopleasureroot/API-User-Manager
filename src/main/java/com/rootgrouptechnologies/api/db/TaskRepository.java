package com.rootgrouptechnologies.api.db;

import com.rootgrouptechnologies.api.db.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
