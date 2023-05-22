package com.thompete.conferenceservice.service;

import com.thompete.conferenceservice.model.Conference;
import com.thompete.conferenceservice.model.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService implements IConferenceService {
    Conference conference;

    @Autowired
    public ConferenceService(Conference conference) {
        this.conference = conference;
    }

    @Override
    public Conference getConference() {
        return conference;
    }

    @Override
    public Lecture getLecture(long id) {
        return conference.getLecture(id);
    }

    @Override
    public List<Lecture> getTimeBlock(int timeBlock) {
        return conference.getTimeBlock(timeBlock);
    }

    @Override
    public int getMaxListenersPerLecture() {
        return conference.getMaxListenersPerLecture();
    }
}
