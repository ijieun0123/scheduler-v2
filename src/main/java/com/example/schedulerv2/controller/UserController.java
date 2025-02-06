package com.example.schedulerv2.controller;

import com.example.schedulerv2.dto.*;
import com.example.schedulerv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저 생성
    @PostMapping("/signup")
    public ResponseEntity<SaveUserResponseDto> save(@RequestBody SaveUserRequestDto saveUserRequestDto){
        SaveUserResponseDto saveUserResponseDto = userService.save(saveUserRequestDto.getUsername(), saveUserRequestDto.getEmail(), saveUserRequestDto.getPassword());

        return new ResponseEntity<>(saveUserResponseDto, HttpStatus.CREATED);
    }

    // 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<ReadUserResponseDto> findById(@PathVariable Long id){
        ReadUserResponseDto readUserResponseDto = userService.findById(id);

        return new ResponseEntity<>(readUserResponseDto, HttpStatus.OK);
    }

    // 유저 전체 조회
    @GetMapping
    public ResponseEntity<List<ReadUserResponseDto>> findAll(){
        List<ReadUserResponseDto> users = userService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // 유저 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateUserResponseDto> update(
            @PathVariable Long id,
            @RequestBody UpdateUserRequestDto updateUserRequestDto
    ){
        UpdateUserResponseDto updateUserResponseDto = userService.update(id, updateUserRequestDto.getUsername(), updateUserRequestDto.getEmail(), updateUserRequestDto.getPasssword());

        return new ResponseEntity<>(updateUserResponseDto, HttpStatus.OK);
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id,
            @RequestBody DeleteUserRequestDto deleteUserRequestDto
    ){
        userService.deleteById(id, deleteUserRequestDto.getEmail(), deleteUserRequestDto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
