package com.thompete.conferenceservice.controller;

import com.thompete.conferenceservice.dto.StatisticsDto;
import com.thompete.conferenceservice.service.IStatisticsService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatisticsController {
    private IStatisticsService statisticsService;

    @Autowired
    public StatisticsController(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public List<StatisticsDto> getStatistics(
            @Pattern(regexp = "(lectures|paths)") @RequestParam("for") String type,
            @Pattern(regexp = "(asc|desc)") @RequestParam String order
    ) {
        return statisticsService.getStatistics(type, order);
    }

}
