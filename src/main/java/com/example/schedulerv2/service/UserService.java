package com.example.schedulerv2.service;

import com.example.schedulerv2.config.BCryptPasswordEncoder;
import com.example.schedulerv2.dto.LoginResponseDto;
import com.example.schedulerv2.dto.UserResponseDto;
import com.example.schedulerv2.entity.User;
import com.example.schedulerv2.repository.UserRepository;
import com.example.schedulerv2.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // 로그인
    public LoginResponseDto login(String email, String password, HttpServletRequest request) {
        if(passwordCheck(email, password)){
            // jwtToken 생성
            String jwtToken = JwtUtil.generateToken(email);

            // 세션에 jwtToken 저장
            HttpSession session = request.getSession();
            session.setAttribute("jwtToken", jwtToken);

            return new LoginResponseDto(jwtToken);
        } else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }

    private boolean passwordCheck(String email, String password){
        User findUser = userRepository.findUserByEmailOrElseThrow(email);

        String storedPassword = findUser.getPassword();

        return BCryptPasswordEncoder.matches(password, storedPassword);
    }

    // 회원가입
    public UserResponseDto save(String username, String email, String password) {
        String hashedPassword = BCryptPasswordEncoder.encode(password);

        User user = new User(username, email, hashedPassword);

        User savedUser = userRepository.save(user);

        return UserResponseDto.toUserDto(savedUser);
    }

    public UserResponseDto findById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        return UserResponseDto.toUserDto(findUser);
    }

    public List<UserResponseDto> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.getContent().stream().map(UserResponseDto::toUserDto).collect(Collectors.toList());
    }

    @Transactional
    public UserResponseDto update(Long id, String username) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        findUser.setUsername(username);

        return UserResponseDto.toUserDto(findUser);
    }

    public void deleteById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }
}
