package com.thompete.conferenceservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Uzer")
public class User {

    @Id
    @Column(name = "login")
    @Length(min = 2, message = "Login musi zawierać co najmniej 2 znaki")
    private String login;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "To nie jest poprawny adres email")
    private String email;

    public User() {}

    public User(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
