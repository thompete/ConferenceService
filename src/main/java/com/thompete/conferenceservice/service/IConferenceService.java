package com.thompete.conferenceservice.service;

import com.thompete.conferenceservice.model.Conference;
import com.thompete.conferenceservice.model.Lecture;
import com.thompete.conferenceservice.model.Path;

import java.util.List;

public interface IConferenceService {
    Conference getConference();
    List<Lecture> getAllLectures();
    Lecture getLecture(long id);
    List<Path> getAllPaths();
    List<Lecture> getTimeBlock(int id);
    int getMaxListenersPerLecture();
}
