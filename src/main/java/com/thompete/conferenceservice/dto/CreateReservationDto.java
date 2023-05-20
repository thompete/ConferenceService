package com.thompete.conferenceservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public class CreateReservationDto {

    @Length(min = 2, message = "Login musi zawierać co najmniej 2 znaki")
    private String login;

    @Email(message = "To nie jest poprawny adres email")
    private String email;
    @Min(value = 0, message = "ID prelekcji musi być dodatnie")
    private long lectureId;

    public CreateReservationDto(String login, String email, long lectureId) {
        this.login = login;
        this.email = email;
        this.lectureId = lectureId;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public long getLectureId() {
        return lectureId;
    }
}
