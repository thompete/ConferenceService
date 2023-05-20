package com.thompete.conferenceservice.repository;

import com.thompete.conferenceservice.model.Reservation;
import com.thompete.conferenceservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserLogin(String login);
    boolean existsByUserAndLectureId(User user, long lectureId);
    int countByLectureId(long lectureId);
}
