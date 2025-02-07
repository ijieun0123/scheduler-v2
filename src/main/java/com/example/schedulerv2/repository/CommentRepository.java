package com.example.schedulerv2.repository;

import com.example.schedulerv2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findCommentById(Long id);

    default Comment findCommentByIdOrElseThrow(Long id) {
        return findCommentById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id: " + id));
    }

    List<Comment> findCommentsByScheduleId(Long scheduleId);

    List<Comment> findCommentsByUserId(Long userId);
}
