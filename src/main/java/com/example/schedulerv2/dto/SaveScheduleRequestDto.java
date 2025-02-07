package com.example.schedulerv2.dto;

import lombok.Getter;

@Getter
public class SaveScheduleRequestDto {

    private final String email;

    private final String title;

    private final String contents;

    public SaveScheduleRequestDto(String email, String title, String contents) {
        this.email = email;
        this.title = title;
        this.contents = contents;
    }
}
