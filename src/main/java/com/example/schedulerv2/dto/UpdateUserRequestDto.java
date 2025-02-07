package com.example.schedulerv2.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    private final String username;

    public UpdateUserRequestDto(String username) {
        this.username = username;
    }
}
