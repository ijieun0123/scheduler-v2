package com.example.schedulerv2.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SaveScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public SaveScheduleResponseDto(Long id, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
