package com.example.schedulerv2.dto.response;

import com.example.schedulerv2.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private final Long id;

    private final String username;

    private final String email;

    private final Integer scheduleSize;

    private final Integer commentSize;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public UserResponseDto(Long id, String username, String email, Integer scheduleSize, Integer commentSize, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.scheduleSize = scheduleSize;
        this.commentSize = commentSize;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static UserResponseDto toUserDto(User user){
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getSchedules().size(), user.getComments().size(), user.getCreatedAt(), user.getModifiedAt());
    }
}
