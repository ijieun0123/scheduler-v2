package com.example.schedulerv2.repository;

import com.example.schedulerv2.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findCommentById(Long id);

    default Comment findCommentByIdOrElseThrow(Long id) {
        return findCommentById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id: " + id));
    }

    Page<Comment> findCommentsByScheduleId(Long scheduleId, Pageable pageable);

    Page<Comment> findCommentsByUserId(Long userId, Pageable pageable);

    Page<Comment> findAll(Pageable pageable);
}
