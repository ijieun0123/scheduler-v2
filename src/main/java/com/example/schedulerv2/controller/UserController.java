package com.example.schedulerv2.controller;

import com.example.schedulerv2.dto.request.LoginRequestDto;
import com.example.schedulerv2.dto.request.SaveUserRequestDto;
import com.example.schedulerv2.dto.request.UpdateUserRequestDto;
import com.example.schedulerv2.dto.response.LoginResponseDto;
import com.example.schedulerv2.dto.response.UserResponseDto;
import com.example.schedulerv2.service.UserService;
import com.example.schedulerv2.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto loginRequestDto,
            HttpServletRequest request
    ){
        HttpSession session = request.getSession();

        LoginResponseDto loginResponseDto = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword(), session);

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    // 유저 생성
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody SaveUserRequestDto saveUserRequestDto){
        UserResponseDto userResponseDto = userService.save(saveUserRequestDto.getUsername(), saveUserRequestDto.getEmail(), saveUserRequestDto.getPassword());

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    // 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 유저 전체 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(@RequestParam Integer pageNumber, Integer pageSize){
        List<UserResponseDto> userResponseDtoList = userService.findAll(pageNumber, pageSize);

        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    // 유저 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequestDto updateUserRequestDto,
            HttpServletRequest request
    ){
        String currentUserEmail = JwtUtil.getEmailFromRequest(request);

        UserResponseDto userResponseDto = userService.update(id, updateUserRequestDto.getUsername(), currentUserEmail);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id,
            HttpServletRequest request
    ){
        String currentUserEmail = JwtUtil.getEmailFromRequest(request);

        userService.deleteById(id, currentUserEmail);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
