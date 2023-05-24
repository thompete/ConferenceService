package com.thompete.conferenceservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Conference {
    private static long lectureCounter = 0;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Path[] plan;
    @JsonIgnore
    private int numberOfTimeBlocks;
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
        this.plan = new Path[numberOfPaths];
        this.numberOfTimeBlocks = numberOfTimeBlocks;
        int confLength = numberOfTimeBlocks * timeBlockLength - breakLength;
        this.endDate = startDate.plusMinutes(confLength);
        this.timeBlockLength = timeBlockLength;
        this.lectureLength = timeBlockLength - breakLength;
        this.maxListenersPerLecture = maxListenersPerLecture;
    }

    public long incrementLectureCounter() {
        return lectureCounter++;
    }

    public Path addPath(String name) {
        Path path = null;
        for (int i = 0; i < plan.length; i++) {
            if (plan[i] == null) {
                plan[i] = path = new Path(i, name, this);
                break;
            }
        }
        return path;
    }

    @JsonIgnore
    public List<Path> getAllPaths() {
        return Arrays.asList(plan);
    }

    public void addLecture(String title, Path path) {
        plan[path.getId()].addLecture(title);
    }

    @JsonIgnore
    public List<Lecture> getAllLectures() {
        return Stream.of(plan).flatMap(path -> path.getLectures().stream()).collect(Collectors.toList());
    }

    public Lecture getLecture(long id) {
        Lecture result = null;
        outer: for (Path path : plan) {
            for (Lecture lecture : path.getLectures()) {
                if (id == lecture.getId()) {
                    result = lecture;
                    break outer;
                }
            }
        }
        return result;
    }

    public List<Lecture> getTimeBlockLectures(int timeBlock) {
        List<Lecture> lectures = new ArrayList<>();
        for (Path path : plan) {
            lectures.add(path.getLecture(timeBlock));
        }
        return lectures;
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

    public Path[] getPlan() {
        return plan;
    }

    public int getNumberOfTimeBlocks() {
        return numberOfTimeBlocks;
    }

    public int getTimeBlockLength() {
        return timeBlockLength;
    }

    public int getMaxListenersPerLecture() {
        return maxListenersPerLecture;
    }

    public int getLectureLength() {
        return lectureLength;
    }

    @Override
    public String toString() {
        return title;
    }
}
