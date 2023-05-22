package com.thompete.conferenceservice.dto;

import com.thompete.conferenceservice.model.Lecture;
import com.thompete.conferenceservice.model.User;

public class GetReservationDto {
    private long id;
    private User user;
    private Lecture lecture;

    public GetReservationDto(long id, User user, Lecture lecture) {
        this.id = id;
        this.user = user;
        this.lecture = lecture;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Lecture getLecture() {
        return lecture;
    }
}
