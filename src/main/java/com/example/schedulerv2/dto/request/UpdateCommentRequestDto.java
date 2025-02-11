package com.example.schedulerv2.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateCommentRequestDto {

    @NotBlank(message = "컨텐츠를 입력하세요.")
    private final String contents;

    public UpdateCommentRequestDto(String contents) {
        this.contents = contents;
    }
}
