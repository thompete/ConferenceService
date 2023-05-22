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
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @Column(name = "idLecture", nullable = false)
    private long lectureId;

    public Reservation() {}

    public Reservation(User user, long lectureId) {
        this.user = user;
        this.lectureId = lectureId;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public long getLectureId() {
        return lectureId;
    }

    @Override
    public String toString() {
        return "User: " + user + " lecture id: " + lectureId;
    }
}
