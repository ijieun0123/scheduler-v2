package com.example.schedulerv2.controller;

import com.example.schedulerv2.dto.CommentResponseDto;
import com.example.schedulerv2.dto.SaveCommentRequestDto;
import com.example.schedulerv2.dto.UpdateScheduleRequestDto;
import com.example.schedulerv2.service.CommentService;
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
    public ResponseEntity<CommentResponseDto> save(@RequestBody SaveCommentRequestDto saveCommentRequestDto){
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
    public ResponseEntity<List<CommentResponseDto>> findByScheduleId(@PathVariable Long scheduleId){
        List<CommentResponseDto> commentResponseDtoList = commentService.findByScheduleId(scheduleId);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    // 댓글 조회 ( by userId )
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentResponseDto>> findByUserId(@PathVariable Long userId){
        List<CommentResponseDto> commentResponseDtoList = commentService.findByUserId(userId);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    // 댓글 전체 조회
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAll(){
        List<CommentResponseDto> commentResponseDtoList = commentService.findAll();

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    // 댓글 수정
    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequestDto updateScheduleRequestDto
    ){
        CommentResponseDto commentResponseDto = commentService.update(id, updateScheduleRequestDto.getContents());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        commentService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
