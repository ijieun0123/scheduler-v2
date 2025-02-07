package com.example.schedulerv2.dto;

import lombok.Getter;

@Getter
public class SaveCommentRequestDto {

    private final String contents;

    private final Long schedule_id;

    private final Long user_id;

    public SaveCommentRequestDto(String contents, Long schedule_id, Long user_id) {
        this.contents = contents;
        this.schedule_id = schedule_id;
        this.user_id = user_id;
    }
}
