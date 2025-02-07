package com.example.schedulerv2.dto;

import com.example.schedulerv2.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    private final String username;

    private final Integer commentSize;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public ScheduleResponseDto(Long id, String title, String contents, String username, Integer commentSize, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.commentSize = commentSize;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ScheduleResponseDto toScheduleDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getUser().getUsername(), schedule.getComments().size(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }
}
