package com.example.schedulerv2.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    private final String username;

    private final String email;

    private final String passsword;

    public UpdateUserRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.passsword = password;
    }
}
