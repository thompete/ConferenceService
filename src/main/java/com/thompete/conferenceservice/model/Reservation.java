package com.thompete.conferenceservice.model;

import jakarta.persistence.*;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User: " + user + " lecture id: " + lectureId;
    }
}
