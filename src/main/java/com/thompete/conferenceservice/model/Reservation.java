package com.thompete.conferenceservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "idReservation")
    private long id;

    @ManyToOne
    @JoinColumn(name = "login", nullable = false)
    private User user;

    @Column(name = "idLecture", nullable = false)
    private long lectureId;

    public Reservation() {}

    public Reservation(User user, long lectureId) {
        this.user = user;
        this.lectureId = lectureId;
    }

    public User getUser() {
        return user;
    }

    public long getLectureId() {
        return lectureId;
    }
}
