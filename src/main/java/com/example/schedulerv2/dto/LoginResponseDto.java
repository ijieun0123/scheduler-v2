package com.example.schedulerv2.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private final String token;

    public LoginResponseDto(String token) {
        this.token = token;
    }
}
