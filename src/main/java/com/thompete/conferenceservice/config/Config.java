package com.thompete.conferenceservice.config;

import com.thompete.conferenceservice.model.Conference;
import com.thompete.conferenceservice.model.Path;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class Config {
    @Bean
    public Conference conference() {
        Conference conference = new Conference(
                "1st International Conference on Placeholder Texts",
                LocalDateTime.of(2023, 4, 26, 10, 0),
                3,
                3,
                120,
                15,
                5
        );

        Path pathA = conference.addPath("Path A");
        Path pathB = conference.addPath("Path B");
        Path pathC = conference.addPath("Path C");

        conference.addLecture("Lorem ipsum dolor sit amet", pathA);
        conference.addLecture("Sed quis bibendum lorem", pathA);
        conference.addLecture("In hac habitasse platea dictumst", pathA);
        conference.addLecture("Vestibulum ante ipsum primis", pathB);
        conference.addLecture("Vivamus quis massa tempus", pathB);
        conference.addLecture("Vestibulum gravida consectetur arcu", pathB);
        conference.addLecture("Quisque egestas ante et convallis volutpat", pathC);
        conference.addLecture("Fusce sit amet imperdiet mi", pathC);
        conference.addLecture("Duis dictum diam augue", pathC);

        return conference;
    }
}
