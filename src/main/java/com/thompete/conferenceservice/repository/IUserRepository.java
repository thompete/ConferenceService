package com.thompete.conferenceservice.repository;

import com.thompete.conferenceservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
}
