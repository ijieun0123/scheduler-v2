package com.example.schedulerv2.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SaveCommentRequestDto {

    @NotBlank(message = "컨텐츠를 입력하세요.")
    private final String contents;

    @NotNull(message = "스케줄 아이디를 입력하세요.")
    private final Long schedule_id;

    @NotNull(message = "유저 아이디를 입력하세요.")
    private final Long user_id;

    public SaveCommentRequestDto(String contents, Long schedule_id, Long user_id) {
        this.contents = contents;
        this.schedule_id = schedule_id;
        this.user_id = user_id;
    }
}
