package com.thompete.conferenceservice.controller;

import com.thompete.conferenceservice.model.Conference;
import com.thompete.conferenceservice.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conference")
public class ConferenceController {

    ConferenceService conferenceService;

    @Autowired
    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping
    public Conference getConference() {
        return conferenceService.getConference();
    }
}
