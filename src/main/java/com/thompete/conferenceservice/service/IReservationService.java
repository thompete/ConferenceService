package com.thompete.conferenceservice.service;

import com.thompete.conferenceservice.dto.CreateReservationDto;
import com.thompete.conferenceservice.dto.GetReservationDto;

import java.util.List;

public interface IReservationService {
    List<GetReservationDto> getReservations(String login);
    GetReservationDto createReservation(CreateReservationDto reservation);
    void deleteReservation(long id);
}
