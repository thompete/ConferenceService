package com.thompete.conferenceservice.service;

import com.thompete.conferenceservice.model.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConferenceServiceImpl implements ConferenceService {
    Conference conference;

    @Autowired
    public ConferenceServiceImpl(Conference conference) {
        this.conference = conference;
    }

    @Override
    public Conference getConference() {
        return conference;
    }
}
