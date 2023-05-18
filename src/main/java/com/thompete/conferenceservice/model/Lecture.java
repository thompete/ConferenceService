package com.thompete.conferenceservice.model;

import java.time.LocalDateTime;

public class Lecture {
    private static int maxListeners = 5;
    private long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Lecture(long id, String title, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static int getMaxListeners() {
        return maxListeners;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
