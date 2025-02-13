package com.example.schedulerv2.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    @NotBlank(message = "타이틀을 입력하세요.")
    private String title;

    @NotBlank(message = "컨텐츠를 입력하세요.")
    private String contents;

    public UpdateScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
