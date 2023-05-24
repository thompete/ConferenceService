package com.thompete.conferenceservice.dto;

import com.thompete.conferenceservice.model.Path;

public class PathStatisticsDto extends StatisticsDto {
    private PathIdAndTitleDto path;

    public PathStatisticsDto(double interestPercentage, Path path) {
        super(interestPercentage);
        this.path = new PathIdAndTitleDto(path.getId(), path.getTitle());
    }

    public PathIdAndTitleDto getPath() {
        return path;
    }
}
