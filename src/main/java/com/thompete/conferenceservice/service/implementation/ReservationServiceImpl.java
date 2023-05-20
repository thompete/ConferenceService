package com.thompete.conferenceservice.service.implementation;

import com.thompete.conferenceservice.dto.CreateReservationDto;
import com.thompete.conferenceservice.error.exception.ConflictException;
import com.thompete.conferenceservice.error.exception.NotFoundException;
import com.thompete.conferenceservice.model.Reservation;
import com.thompete.conferenceservice.model.User;
import com.thompete.conferenceservice.repository.ReservationRepository;
import com.thompete.conferenceservice.service.ConferenceService;
import com.thompete.conferenceservice.service.ReservationService;
import com.thompete.conferenceservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository;
    UserService userService;
    ConferenceService conferenceService;

    @Autowired
    public ReservationServiceImpl(
            ReservationRepository reservationRepository, UserService userService, ConferenceService conferenceService
    ) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.conferenceService = conferenceService;
    }

    @Override
    public List<Reservation> getReservations(String login) {
        if (!userService.exists(login)) {
            throw new NotFoundException("Nie znaleziono użytkownika: " + login);
        }
        return reservationRepository.findByUserLogin(login);
    }

    @Override
    public Reservation createReservation(CreateReservationDto dto) {
        String login = dto.getLogin();
        String email = dto.getEmail();
        long lectureId = dto.getLectureId();

        if (conferenceService.getLecture(lectureId) == null) {
            throw new NotFoundException("Nie znaleziono prelekcji: " + lectureId);
        }

        User user = new User(login, email);

        if (!userService.exists(login)) {
            userService.createUser(user);
        } else if (reservationRepository.existsByUserAndLectureId(user, lectureId)) {
            throw new ConflictException("Użytkownik jest już zapisany na tę prelekcję");
        } else if (getReservations(login).stream().anyMatch(reservation ->
                        conferenceService.getLecture(reservation.getLectureId()).getTimeBlock() ==
                        conferenceService.getLecture(lectureId).getTimeBlock())
        ) {
            throw new ConflictException("Użytkownik jest już zapisany na inną prelekcję w tym bloku czasowym");
        }

        if (reservationRepository.countByLectureId(lectureId) == conferenceService.getMaxListenersPerLecture()) {
            throw new ConflictException("Limit miejsc na tę prelekcję został wyczerpany");
        }

        return reservationRepository.save(new Reservation(user, lectureId));
    }

    @Override
    public void deleteReservation(long id) {
        if (reservationRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Nie znaleziono rezerwacji: " + id);
        }
        reservationRepository.deleteById(id);
    }
}
