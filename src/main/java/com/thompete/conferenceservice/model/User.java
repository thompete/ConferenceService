package com.thompete.conferenceservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Uzer")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "idUser")
    private long id;

    @Column(name = "login", unique = true, nullable = false)
    @Length(min = 2, message = "Login should have at least 2 characters")
    private String login;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    private String email;

    public User() {}

    public User(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public long getId() {
        return id;
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
