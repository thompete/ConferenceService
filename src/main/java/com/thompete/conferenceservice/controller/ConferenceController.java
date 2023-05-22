package com.thompete.conferenceservice.controller;

import com.thompete.conferenceservice.model.Conference;
import com.thompete.conferenceservice.service.IConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conference")
public class ConferenceController {

    IConferenceService conferenceService;

    @Autowired
    public ConferenceController(IConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping
    public Conference getConference() {
        return conferenceService.getConference();
    }
}
