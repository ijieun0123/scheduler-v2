package com.example.schedulerv2.repository;

import com.example.schedulerv2.entity.Comment;

import java.util.List;

public interface CommentsRepositoryCustom {
    List<Comment> findByScheduleIdAndUserId(Long scheduleId, Long userId);
}
