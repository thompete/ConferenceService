package com.thompete.conferenceservice.controller;

import com.thompete.conferenceservice.dto.CreateReservationDto;
import com.thompete.conferenceservice.dto.GetReservationDto;
import com.thompete.conferenceservice.service.IReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    IReservationService reservationService;

    @Autowired
    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{login}")
    public List<GetReservationDto> getReservations(@PathVariable String login) {
        return reservationService.getReservations(login);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GetReservationDto createReservation(@Valid @RequestBody CreateReservationDto createReservationDto) {
        return reservationService.createReservation(createReservationDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
    }

}
