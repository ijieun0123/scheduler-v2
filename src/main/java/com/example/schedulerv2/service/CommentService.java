package com.example.schedulerv2.service;

import com.example.schedulerv2.dto.CommentResponseDto;
import com.example.schedulerv2.entity.Comment;
import com.example.schedulerv2.entity.Schedule;
import com.example.schedulerv2.entity.User;
import com.example.schedulerv2.repository.CommentRepository;
import com.example.schedulerv2.repository.ScheduleRepository;
import com.example.schedulerv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CommentResponseDto save(String contents, Long scheduleId, Long userId) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        User findUser = userRepository.findUserByIdOrElseThrow(userId);

        Comment comment = new Comment(contents, findSchedule, findUser);

        Comment savedComment = commentRepository.save(comment);

        return CommentResponseDto.toCommentDto(savedComment);
    }

    public CommentResponseDto findByCommentId(Long commentId) {
        Comment findComment = commentRepository.findCommentByIdOrElseThrow(commentId);

        return CommentResponseDto.toCommentDto(findComment);
    }

    public List<CommentResponseDto> findByScheduleId(Long scheduleId, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        Page<Comment> commentPage = commentRepository.findCommentsByScheduleId(scheduleId, pageable);

        return commentPage.getContent().stream().map(CommentResponseDto::toCommentDto).collect(Collectors.toList());
    }


    public List<CommentResponseDto> findByUserId(Long userId, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        Page<Comment> commentPage = commentRepository.findCommentsByUserId(userId, pageable);

        return commentPage.getContent().stream().map(CommentResponseDto::toCommentDto).collect(Collectors.toList());
    }

    public List<CommentResponseDto> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        Page<Comment> commentPage = commentRepository.findAll(pageable);

        return commentPage.getContent().stream().map(CommentResponseDto::toCommentDto).collect(Collectors.toList());
    }

    public CommentResponseDto update(Long id, String contents) {
        Comment findComment = commentRepository.findCommentByIdOrElseThrow(id);

        findComment.setContents(contents);

        return CommentResponseDto.toCommentDto(findComment);
    }

    public void deleteById(Long id) {
        Comment findComment = commentRepository.findCommentByIdOrElseThrow(id);

        commentRepository.delete(findComment);
    }
}
