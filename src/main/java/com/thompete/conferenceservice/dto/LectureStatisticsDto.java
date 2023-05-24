package com.thompete.conferenceservice.dto;

import com.thompete.conferenceservice.model.Lecture;

public class LectureStatisticsDto extends StatisticsDto{
    private Lecture lecture;

    public LectureStatisticsDto(Lecture lecture, double interestPercentage) {
        super(interestPercentage);
        this.lecture = lecture;
    }

    public Lecture getLecture() {
        return lecture;
    }
}
