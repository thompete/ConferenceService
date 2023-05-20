package com.thompete.conferenceservice.controller;

import com.thompete.conferenceservice.dto.CreateReservationDto;
import com.thompete.conferenceservice.model.Reservation;
import com.thompete.conferenceservice.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{login}")
    public List<Reservation> getReservations(@PathVariable String login) {
        return reservationService.getReservations(login);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@Valid @RequestBody CreateReservationDto createReservationDto) {
        return reservationService.createReservation(createReservationDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
    }

}
