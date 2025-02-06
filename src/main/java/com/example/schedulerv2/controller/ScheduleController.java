package com.example.schedulerv2.controller;

import com.example.schedulerv2.dto.*;
import com.example.schedulerv2.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 스케줄 생성
    @PostMapping
    public ResponseEntity<SaveScheduleResponseDto> save(@RequestBody SaveScheduleRequestDto saveScheduleRequestDto){
        SaveScheduleResponseDto saveScheduleResponseDto = scheduleService.save(saveScheduleRequestDto.getEmail(), saveScheduleRequestDto.getPassword(), saveScheduleRequestDto.getTitle(), saveScheduleRequestDto.getPassword());

        return new ResponseEntity<>(saveScheduleResponseDto, HttpStatus.CREATED);
    }

    // 스케줄 조회
    @GetMapping("/{id}")
    public ResponseEntity<ReadScheduleResponseDto> findById(@PathVariable Long id){
        ReadScheduleResponseDto readScheduleResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(readScheduleResponseDto, HttpStatus.OK);
    }

    // 스케줄 전체 조회
    @GetMapping
    public ResponseEntity<List<ReadScheduleResponseDto>> findAll(){
        List<ReadScheduleResponseDto> schedules = scheduleService.findAll();

        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    // 스케줄 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateScheduleResponseDto> update(
        @PathVariable Long id,
        @RequestBody UpdateScheduleRequestDto updateScheduleRequestDto
    ){
        UpdateScheduleResponseDto updateScheduleResponseDto = scheduleService.update(id, updateScheduleRequestDto.getEmail(), updateScheduleRequestDto.getPassword(), updateScheduleRequestDto.getTitle(), updateScheduleRequestDto.getContents());

        return new ResponseEntity<>(updateScheduleResponseDto, HttpStatus.OK);
    }

    // 스케줄 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        scheduleService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
