package com.thompete.conferenceservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class Conference {
    private static long lectureCounter = 0L;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Lecture[][] plan;
    @JsonIgnore
    private int timeBlockLength;
    @JsonIgnore
    private int lectureLength;

    public Conference(
            String title, LocalDateTime startDate, int numberOfPaths,
            int numberOfTimeBlocks, int timeBlockLength, int breakLength
    ) {
        this.title = title;
        this.startDate = startDate;
        this.plan = new Lecture[numberOfTimeBlocks][numberOfPaths];
        int confLength = numberOfTimeBlocks * timeBlockLength - breakLength;
        this.endDate = startDate.plusMinutes(confLength);
        this.timeBlockLength = timeBlockLength;
        this.lectureLength = timeBlockLength - breakLength;
    }

    public void addLecture(String title, int timeBlock, int path) {
        LocalDateTime start = startDate.plusMinutes((long) timeBlock * timeBlockLength);
        LocalDateTime end = start.plusMinutes(lectureLength);
        plan[timeBlock][path] = new Lecture(lectureCounter++, title, start, end);
    }

    public Lecture getLecture(int timeBlock, int path) {
        return plan[timeBlock][path];
    }

    public Lecture getLecture(long id) {
        Lecture result = null;
        outer: for (Lecture[] timeBlock : plan) {
            for (Lecture lecture : timeBlock) {
                if (id == lecture.getId()) {
                    result = lecture;
                    break outer;
                }
            }
        }
        return result;
    }
}
