package com.thompete.conferenceservice.service.impl;

import com.thompete.conferenceservice.dto.LectureStatisticsDto;
import com.thompete.conferenceservice.dto.StatisticsDto;
import com.thompete.conferenceservice.model.Lecture;
import com.thompete.conferenceservice.service.IConferenceService;
import com.thompete.conferenceservice.service.IReservationService;
import com.thompete.conferenceservice.service.IStatisticsService;
import com.thompete.conferenceservice.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class StatisticsService implements IStatisticsService {

    private IReservationService reservationService;
    private IConferenceService conferenceService;

    @Autowired
    public StatisticsService(IReservationService reservationService, IConferenceService conferenceService) {
        this.reservationService = reservationService;
        this.conferenceService = conferenceService;
    }

    @Override
    public List<StatisticsDto> getStatistics(String type, String order) {
        List<StatisticsDto> stats = new ArrayList<>();
        long total = reservationService.countReservations();

        switch (type) {
            case "lectures" -> stats = getLectureStatistics(stats, total);
            case "paths" -> stats = getPathStatistics(stats, total);
        }
        switch (order) {
            case "asc" -> stats.sort(Comparator.comparing(StatisticsDto::getInterestPercentage));
            case "desc" -> stats.sort(Comparator.comparing(StatisticsDto::getInterestPercentage).reversed());
        }
        return stats;
    }

    private List<StatisticsDto> getLectureStatistics(List<StatisticsDto> stats, long total) {
        List<Lecture> lectures = conferenceService.getAllLectures();
        lectures.forEach(lecture -> stats.add(new LectureStatisticsDto(
                lecture,
                Utils.round(
                        (double) reservationService.countReservationsByLectureId(lecture.getId()) / total * 100,
                        2
                )
        )));
        return stats;
    }

    private List<StatisticsDto> getPathStatistics(List<StatisticsDto> stats, long total) {
        return null;
    }
}
