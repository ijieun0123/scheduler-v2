package com.example.schedulerv2.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    private final String username;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public UpdateScheduleResponseDto(Long id, String title, String contents, String username, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
