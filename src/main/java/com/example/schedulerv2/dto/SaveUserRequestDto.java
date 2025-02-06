package com.example.schedulerv2.dto;

import lombok.Getter;

@Getter
public class SaveUserRequestDto {

    private final String username;

    private final String email;

    private final String password;

    public SaveUserRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
