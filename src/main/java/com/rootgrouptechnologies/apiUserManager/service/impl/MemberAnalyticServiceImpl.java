package com.rootgrouptechnologies.apiUserManager.service.impl;

import com.rootgrouptechnologies.apiUserManager.entity.Metric;
import com.rootgrouptechnologies.apiUserManager.model.DTO.DepartedUserDTO;
import com.rootgrouptechnologies.apiUserManager.model.DTO.MetricDTO;
import com.rootgrouptechnologies.apiUserManager.model.mapper.ObjectMapper;
import com.rootgrouptechnologies.apiUserManager.model.request.PeriodTimeRequest;
import com.rootgrouptechnologies.apiUserManager.repository.MetricRepository;
import com.rootgrouptechnologies.apiUserManager.repository.UserRepository;
import com.rootgrouptechnologies.apiUserManager.service.MemberAnalyticService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class MemberAnalyticServiceImpl implements MemberAnalyticService {
    private final UserRepository userRepository;
    private final MetricRepository metricRepository;

    @Override
    @Scheduled(cron = "0 08 2 ? * *", zone = "GMT+3")
    public void collectAndRecordMetrics() {
        Metric metric = new Metric();

        int currentUsersQuantity = userRepository.findAll().size();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        metric.setQuantity(currentUsersQuantity);
        metric.setDate(date);

        Metric previousDayMetric = metricRepository.findMetricByDate(LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        metric.setDepartedUsers(calculateDepartedUsers(currentUsersQuantity, previousDayMetric));

        metricRepository.save(metric);
    }

    @Override
    public DepartedUserDTO getQuantityDepartedUsers(PeriodTimeRequest periodTimeRequest) {
        DepartedUserDTO departedUserDTO = new DepartedUserDTO();
        List<Metric> metrics = metricRepository.findMetricsByDateBetween(periodTimeRequest.getStartDate(), periodTimeRequest.getEndDate());
        List<Metric> allMetrics = metricRepository.findAll();

        Metric startMetric = metrics.get(0);
        Metric endMetric = metrics.get(metrics.size() - 1);

        int departedUsers = startMetric.getQuantity() - endMetric.getQuantity();
        int incomeUsers = endMetric.getQuantity() - startMetric.getQuantity();

        if (departedUsers <= 0) departedUsers = 0;
        if (incomeUsers <= 0) incomeUsers = 0;

        departedUserDTO.setQuantityDepartedUsers(departedUsers);
        departedUserDTO.setPeriodTimeRequest(periodTimeRequest);
        departedUserDTO.setRetentionPercentage(calculateRetentionPercentage(startMetric.getQuantity(), endMetric.getQuantity()));
        departedUserDTO.setQuantityIncomeUsers(incomeUsers);
        departedUserDTO.setListMetrics(convertToDTOMetrics(allMetrics));

        return departedUserDTO;
    }

    private List<MetricDTO> convertToDTOMetrics(List<Metric> metrics) {
        List<MetricDTO> list = new LinkedList<>();

        for (Metric metric : metrics) {
            list.add(ObjectMapper.INSTANCE.toMetricDTO(metric));
        }

        return list;
    }

    private int calculateDepartedUsers(int todayQtyMembers, Metric previousDayMetric) {
        int departedUsers = todayQtyMembers - previousDayMetric.getQuantity();

        if (departedUsers > 0) departedUsers = 0;
        if (departedUsers < 0) departedUsers = -departedUsers;

        return departedUsers;
    }

    private double calculateRetentionPercentage(Integer startQty, Integer endQty) {
        double retentionPercentage;
        int incomeUsers;

        if (startQty - endQty < 0) {
            incomeUsers = -(endQty - startQty);
        } else {
            incomeUsers = 0;
        }

        retentionPercentage = Math.round((endQty.doubleValue() - incomeUsers)/startQty*100);

        return retentionPercentage;
    }

}
