package com.example.schedulerv2.dto;

import lombok.Getter;

@Getter
public class SaveScheduleRequestDto {

    private final String title;

    private final String contents;

    private final String username;

    public SaveScheduleRequestDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}
