package com.thompete.conferenceservice.dto;

public class StatisticsDto {
    private double interestPercentage;

    public StatisticsDto(double interestPercentage) {
        this.interestPercentage = interestPercentage;
    }

    public double getInterestPercentage() {
        return interestPercentage;
    }
}
