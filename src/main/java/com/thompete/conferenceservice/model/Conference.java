package com.thompete.conferenceservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Conference {
    private static long lectureCounter = 0;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Lecture[][] plan;
    @JsonIgnore
    private int timeBlockLength;
    @JsonIgnore
    private int lectureLength;
    @JsonIgnore
    private int maxListenersPerLecture;

    public Conference(
            String title, LocalDateTime startDate, int numberOfPaths, int numberOfTimeBlocks,
            int timeBlockLength, int breakLength, int maxListenersPerLecture
    ) {
        this.title = title;
        this.startDate = startDate;
        this.plan = new Lecture[numberOfTimeBlocks][numberOfPaths];
        int confLength = numberOfTimeBlocks * timeBlockLength - breakLength;
        this.endDate = startDate.plusMinutes(confLength);
        this.timeBlockLength = timeBlockLength;
        this.lectureLength = timeBlockLength - breakLength;
        this.maxListenersPerLecture = maxListenersPerLecture;
    }

    public void addLecture(String title, int timeBlock, int path) {
        LocalDateTime start = startDate.plusMinutes((long) timeBlock * timeBlockLength);
        LocalDateTime end = start.plusMinutes(lectureLength);
        plan[timeBlock][path] = new Lecture(lectureCounter++, title, start, end, timeBlock, path);
    }

    @JsonIgnore
    public List<Lecture> getAllLectures() {
        return Stream.of(plan).flatMap(Stream::of).collect(Collectors.toList());
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

    public List<Lecture> getTimeBlock(int timeBlock) {
        return Arrays.asList(plan[timeBlock]);
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Lecture[][] getPlan() {
        return plan;
    }

    public int getMaxListenersPerLecture() {
        return maxListenersPerLecture;
    }

    @Override
    public String toString() {
        return title;
    }
}
