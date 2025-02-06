package com.example.schedulerv2.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SaveUserResponseDto {

    private final Long id;

    private final String username;

    private final String email;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public SaveUserResponseDto(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
