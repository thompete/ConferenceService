package com.thompete.conferenceservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name = "Uzer")
public class User {

    @Id
    @Column(name = "idUser")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return login;
    }
}
