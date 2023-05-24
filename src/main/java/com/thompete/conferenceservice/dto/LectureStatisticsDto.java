package com.thompete.conferenceservice.dto;

import com.thompete.conferenceservice.model.Lecture;

public class LectureStatisticsDto extends StatisticsDto {
    private Lecture lecture;

    public LectureStatisticsDto(double interestPercentage, Lecture lecture) {
        super(interestPercentage);
        this.lecture = lecture;
    }

    public Lecture getLecture() {
        return lecture;
    }
}
