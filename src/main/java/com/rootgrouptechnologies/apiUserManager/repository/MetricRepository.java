package com.rootgrouptechnologies.apiUserManager.repository;

import com.rootgrouptechnologies.apiUserManager.entity.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Integer> {
    List<Metric> findMetricsByDateBetween(String startDate, String endDate);

    Metric findMetricByDate(String date);
}
