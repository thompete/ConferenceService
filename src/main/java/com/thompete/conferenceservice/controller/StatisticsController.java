package com.thompete.conferenceservice.controller;

import com.thompete.conferenceservice.service.IStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatisticsController {
    private IStatsService statsService;

    @Autowired
    public StatisticsController(IStatsService statsService) {
        this.statsService = statsService;
    }

}
