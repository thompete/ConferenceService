package com.thompete.conferenceservice.service;

import com.thompete.conferenceservice.dto.CreateReservationDto;
import com.thompete.conferenceservice.dto.GetReservationDto;
import com.thompete.conferenceservice.model.Lecture;
import com.thompete.conferenceservice.model.Path;

import java.util.List;

public interface IReservationService {
    List<GetReservationDto> getAllReservations();
    List<GetReservationDto> getReservations(String login);
    GetReservationDto createReservation(CreateReservationDto reservation);
    void deleteReservation(long id);

    long countReservations();
    long countReservationsByLecture(Lecture lecture);
    long countReservationsByPath(Path path);
}
