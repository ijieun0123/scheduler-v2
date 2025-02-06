package com.example.schedulerv2.dto;

import lombok.Getter;

@Getter
public class DeleteUserRequestDto {

    private final String email;

    private final String password;

    public DeleteUserRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
