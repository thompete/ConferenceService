package com.thompete.conferenceservice.service;

import com.thompete.conferenceservice.dto.CreateReservationDto;
import com.thompete.conferenceservice.model.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getReservations(String login);
    Reservation createReservation(CreateReservationDto reservation);
    void deleteReservation(long id);
}
