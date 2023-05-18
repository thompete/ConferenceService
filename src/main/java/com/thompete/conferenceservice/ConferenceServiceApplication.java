package com.thompete.conferenceservice;

import com.thompete.conferenceservice.model.Conference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class ConferenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceServiceApplication.class, args);
    }

    @Bean
    public Conference conference() {
        Conference conference = new Conference(
                "Lorem ipsum dolor sit amet",
                LocalDateTime.of(2023, 4, 26, 10, 0),
                3,
                3,
                120,
                15
        );
        conference.addLecture("Sed quis bibendum lorem", 0, 0);
        conference.addLecture("In hac habitasse platea dictumst", 0, 1);
        conference.addLecture("Vestibulum ante ipsum primis", 0, 2);
        conference.addLecture("Vivamus quis massa tempus", 1, 0);
        conference.addLecture("Vestibulum gravida consectetur arcu", 1, 1);
        conference.addLecture("Quisque egestas ante et convallis volutpat", 1, 2);
        conference.addLecture("Fusce sit amet imperdiet mi", 2, 0);
        conference.addLecture("Duis dictum diam augue", 2, 1);
        conference.addLecture("Morbi interdum condimentum erat", 2, 2);

        return conference;
    }
}
