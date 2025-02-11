package com.example.schedulerv2.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SaveScheduleRequestDto {

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private final String email;

    @NotBlank(message = "타이틀을 입력하세요.")
    private final String title;

    @NotBlank(message = "컨텐츠를 입력하세요.")
    private final String contents;

    public SaveScheduleRequestDto(String email, String title, String contents) {
        this.email = email;
        this.title = title;
        this.contents = contents;
    }
}
