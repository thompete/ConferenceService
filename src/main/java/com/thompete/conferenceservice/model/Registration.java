package com.thompete.conferenceservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Registration")
public class Registration {

    @Id
    @GeneratedValue
    @Column(name = "idRegistration")
    private long id;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @Column(name = "idLecture", nullable = false)
    private long lectureId;

    public Registration() {}

    public Registration(User user, long lectureId) {
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
