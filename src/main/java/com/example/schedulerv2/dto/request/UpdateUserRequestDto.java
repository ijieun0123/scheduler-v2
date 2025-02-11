package com.example.schedulerv2.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    @NotBlank(message = "이름을 입력하세요.")
    @Size(min = 2, max = 10, message = "2 ~ 10 자를 입력하세요.")
    private final String username;

    public UpdateUserRequestDto(String username) {
        this.username = username;
    }
}
