package com.thompete.conferenceservice.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Path {
    private int id;
    private String title;
    private Lecture[] lectures;
    private Conference conference;

    public Path(int id, String title, Conference conference) {
        this.id = id;
        this.title = title;
        this.conference = conference;
        lectures = new Lecture[conference.getNumberOfTimeBlocks()];
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Lecture getLecture(int timeBlock) {
        return lectures[timeBlock];
    }

    public List<Lecture> getLectures() {
        return Arrays.asList(lectures);
    }
    public Lecture addLecture(String title) {
        Lecture lecture = null;
        for (int i = 0; i < lectures.length; i++) {
            if (lectures[i] == null) {
                LocalDateTime lectureStart = conference.getStartDate().plusMinutes((long) i * conference.getTimeBlockLength());
                LocalDateTime lectureEnd = lectureStart.plusMinutes(conference.getLectureLength());
                lectures[i] = lecture = new Lecture(
                        conference.incrementLectureCounter(), title, lectureStart, lectureEnd, this, i
                );
                break;
            }
        }
        return lecture;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return id == path.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
