package com.thompete.conferenceservice.dto;

public class PathIdAndTitleDto {
    private int id;
    private String title;

    public PathIdAndTitleDto(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
