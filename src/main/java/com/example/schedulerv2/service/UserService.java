package com.example.schedulerv2.service;

import com.example.schedulerv2.config.BCryptPasswordEncoder;
import com.example.schedulerv2.dto.LoginResponseDto;
import com.example.schedulerv2.dto.ReadUserResponseDto;
import com.example.schedulerv2.dto.SaveUserResponseDto;
import com.example.schedulerv2.dto.UpdateUserResponseDto;
import com.example.schedulerv2.entity.User;
import com.example.schedulerv2.repository.UserRepository;
import com.example.schedulerv2.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil = new JwtUtil();

    // 로그인
    public LoginResponseDto login(String email, String password, HttpServletRequest request) {
        if(validateUser(email, password)){
            // jwtToken 생성
            String jwtToken = jwtUtil.generateToken(email);

            // 세션에 jwtToken 저장
            HttpSession session = request.getSession();
            session.setAttribute("jwtToken", jwtToken);

            return new LoginResponseDto(jwtToken);
        } else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }

    private boolean validateUser(String email, String password){
        User findUser = userRepository.findUserByEmailOrElseThrow(email);

        String storedPassword = findUser.getPassword();

        return BCryptPasswordEncoder.matches(password, storedPassword);
    }

    // 회원가입
    public SaveUserResponseDto save(String username, String email, String password) {
        String hashedPassword = BCryptPasswordEncoder.encode(password);

        User user = new User(username, email, hashedPassword);

        User savedUser = userRepository.save(user);

        return new SaveUserResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getCreatedAt(), savedUser.getModifiedAt());
    }

    public ReadUserResponseDto findById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        return new ReadUserResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail(), findUser.getCreatedAt(), findUser.getModifiedAt());
    }

    public List<ReadUserResponseDto> findAll() {
        List<User> findUsers = userRepository.findAll();

        return findUsers.stream().map(findUser -> {
            return new ReadUserResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail(), findUser.getCreatedAt(), findUser.getModifiedAt());
        }).collect(Collectors.toList());
    }

    @Transactional
    public UpdateUserResponseDto update(Long id, String username) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        findUser.setUsername(username);

        return new UpdateUserResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail(), findUser.getCreatedAt(), findUser.getModifiedAt());
    }

    public void deleteById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }
}
