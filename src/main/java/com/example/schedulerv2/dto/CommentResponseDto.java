package com.example.schedulerv2.dto;

import com.example.schedulerv2.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private final Long id;

    private final String contents;

    private final String scheduleTitle;

    private final String username;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public CommentResponseDto(Long id, String contents, String scheduleTitle, String username, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.contents = contents;
        this.scheduleTitle = scheduleTitle;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CommentResponseDto toCommentDto(Comment comment){
        return new CommentResponseDto(comment.getId(), comment.getContents(), comment.getSchedule().getTitle(), comment.getUser().getUsername(), comment.getCreatedAt(), comment.getModifiedAt());
    }
}
