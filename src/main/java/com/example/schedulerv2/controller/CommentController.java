package com.example.schedulerv2.controller;

import com.example.schedulerv2.dto.response.CommentResponseDto;
import com.example.schedulerv2.dto.request.SaveCommentRequestDto;
import com.example.schedulerv2.dto.request.UpdateCommentRequestDto;
import com.example.schedulerv2.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponseDto> save(@Valid @RequestBody SaveCommentRequestDto saveCommentRequestDto){
        CommentResponseDto commentResponseDto = commentService.save(saveCommentRequestDto.getContents(), saveCommentRequestDto.getSchedule_id(), saveCommentRequestDto.getUser_id());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    // 댓글 조회 ( by commentId )
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> findByCommentId(@PathVariable Long commentId){
        CommentResponseDto commentResponseDto = commentService.findByCommentId(commentId);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    // 댓글 조회 ( by scheduleId )
    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> findByScheduleId(
            @PathVariable Long scheduleId,
            @RequestParam Integer pageNumber, Integer pageSize
    ){
        List<CommentResponseDto> commentResponseDtoList = commentService.findByScheduleId(scheduleId, pageNumber, pageSize);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    // 댓글 조회 ( by userId )
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentResponseDto>> findByUserId(
            @PathVariable Long userId,
            @RequestParam Integer pageNumber, Integer pageSize
    ){
        List<CommentResponseDto> commentResponseDtoList = commentService.findByUserId(userId, pageNumber, pageSize);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    // 댓글 전체 조회
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAll(@RequestParam Integer pageNumber, Integer pageSize){
        List<CommentResponseDto> commentResponseDtoList = commentService.findAll(pageNumber, pageSize);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    // 댓글 수정
    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCommentRequestDto updateCommentRequestDto
    ){
        CommentResponseDto commentResponseDto = commentService.update(id, updateCommentRequestDto.getContents());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        commentService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
