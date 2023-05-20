package com.thompete.conferenceservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class Lecture {
    private long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @JsonIgnore
    private int timeBlock;
    private int path;

    public Lecture(long id, String title, LocalDateTime startDate, LocalDateTime endDate, int timeBlock, int path) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeBlock = timeBlock;
        this.path = path;
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

    public int getTimeBlock() {
        return timeBlock;
    }

    public int getPath() {
        return path;
    }
}
