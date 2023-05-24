package com.thompete.conferenceservice.service.impl;

import com.thompete.conferenceservice.component.IMailer;
import com.thompete.conferenceservice.dto.CreateReservationDto;
import com.thompete.conferenceservice.dto.GetReservationDto;
import com.thompete.conferenceservice.exception.type.ConflictException;
import com.thompete.conferenceservice.exception.type.NotFoundException;
import com.thompete.conferenceservice.exception.type.UnauthorizedException;
import com.thompete.conferenceservice.model.Lecture;
import com.thompete.conferenceservice.model.Path;
import com.thompete.conferenceservice.model.Reservation;
import com.thompete.conferenceservice.model.User;
import com.thompete.conferenceservice.repository.IReservationRepository;
import com.thompete.conferenceservice.service.IConferenceService;
import com.thompete.conferenceservice.service.IReservationService;
import com.thompete.conferenceservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService implements IReservationService {

    IReservationRepository reservationRepository;
    IUserService userService;
    IConferenceService conferenceService;
    IMailer mailer;

    @Autowired
    public ReservationService(
            IReservationRepository reservationRepository, IUserService userService,
            IConferenceService conferenceService, IMailer mailer
    ) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.conferenceService = conferenceService;
        this.mailer = mailer;
    }

    @Override
    public List<GetReservationDto> getAllReservations() {
        return reservationsToGetReservationDtos(reservationRepository.findAll());
    }

    @Override
    public List<GetReservationDto> getReservations(String login) {
        if (!userService.exists(login)) {
            throw new NotFoundException("Nie znaleziono użytkownika: " + login);
        }
        return reservationsToGetReservationDtos(reservationRepository.findByUserLogin(login));
    }

    @Override
    public GetReservationDto createReservation(CreateReservationDto dto) {
        Lecture lecture = conferenceService.getLecture(dto.getLectureId());
        User user = new User(dto.getLogin(), dto.getEmail());

        if (conferenceService.getLecture(lecture.getId()) == null) {
            throw new NotFoundException("Nie znaleziono prelekcji: " + lecture.getId());
        }

        if (!userService.exists(dto.getLogin())) {
            userService.createUser(user);
        } else {
            user = userService.getUser(dto.getLogin()).get();

            if (!dto.getEmail().equals(user.getEmail())) {
                throw new UnauthorizedException("Niepoprawny adres email");
            }

            if (reservationRepository.existsByUserAndLectureId(user, lecture.getId())) {
                throw new ConflictException("Użytkownik jest już zapisany na tę prelekcję");
            } else if (getReservations(user.getLogin()).stream().anyMatch(reservation ->
                    conferenceService.getLecture(reservation.getLecture().getId()).getTimeBlock() ==
                            conferenceService.getLecture(lecture.getId()).getTimeBlock())
            ) {
                throw new ConflictException("Użytkownik jest już zapisany na inną prelekcję w tym czasie");
            }
        }

        if (reservationRepository.countByLectureId(lecture.getId()) == conferenceService.getMaxListenersPerLecture()) {
            throw new ConflictException("Limit miejsc na tę prelekcję został wyczerpany");
        }

        mailer.send(user.getEmail(), "Zostałeś zapisany na prelekcję \"" + lecture + '"');

        Reservation reservation = reservationRepository.save(new Reservation(user, lecture.getId()));

        return new GetReservationDto(reservation.getId(), reservation.getUser(), lecture);
    }

    @Override
    public void deleteReservation(long id) {
        if (reservationRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Nie znaleziono rezerwacji: " + id);
        }
        reservationRepository.deleteById(id);
    }

    @Override
    public long countReservations() {
        return reservationRepository.count();
    }

    @Override
    public long countReservationsByLecture(Lecture lecture) {
        return reservationRepository.countByLectureId(lecture.getId());
    }

    @Override
    public long countReservationsByPath(Path path) {
        long counter = 0;
        List<GetReservationDto> reservations = getAllReservations();
        for (GetReservationDto reservation : reservations) {
            if (reservation.getLecture().getPathId() == path.getId()) counter++;
        }
        return counter;
    }

    private List<GetReservationDto> reservationsToGetReservationDtos(List<Reservation> reservations) {
        return reservations.stream()
                .map(r -> new GetReservationDto(r.getId(), r.getUser(), conferenceService.getLecture(r.getLectureId())))
                .collect(Collectors.toList());
    }
}
