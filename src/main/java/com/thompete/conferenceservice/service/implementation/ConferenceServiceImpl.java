package com.thompete.conferenceservice.service.implementation;

import com.thompete.conferenceservice.model.Conference;
import com.thompete.conferenceservice.model.Lecture;
import com.thompete.conferenceservice.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
