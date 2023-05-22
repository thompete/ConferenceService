package com.thompete.conferenceservice.service;

import com.thompete.conferenceservice.model.Conference;
import com.thompete.conferenceservice.model.Lecture;

import java.util.List;

public interface IConferenceService {
    Conference getConference();
    Lecture getLecture(long id);

    List<Lecture> getTimeBlock(int id);
    int getMaxListenersPerLecture();
}
