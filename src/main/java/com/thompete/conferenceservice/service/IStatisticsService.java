package com.thompete.conferenceservice.service;

import com.thompete.conferenceservice.dto.StatisticsDto;

import java.util.List;

public interface IStatisticsService {
    List<StatisticsDto> getStatistics(String type, String order);
}
