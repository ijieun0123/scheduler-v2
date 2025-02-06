package com.example.schedulerv2.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReadUserResponseDto {

    private final String username;

    private final String email;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public ReadUserResponseDto(String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
