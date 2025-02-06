package com.example.schedulerv2.service;

import com.example.schedulerv2.dto.ReadUserResponseDto;
import com.example.schedulerv2.dto.SaveUserResponseDto;
import com.example.schedulerv2.dto.UpdateUserResponseDto;
import com.example.schedulerv2.entity.User;
import com.example.schedulerv2.repository.UserRepository;
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

    public SaveUserResponseDto save(String username, String email, String password) {
        User user = new User(username, email, password);

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
    public UpdateUserResponseDto update(Long id, String username, String email, String password) {
        // 비밀번호, 이메일 찾기
        User findUser = userRepository.findByIdOrElseThrow(id);
        String storedPasssword = findUser.getPassword();
        String storedEmail = findUser.getEmail();

        // 비밀번호나 이메일이 틀리다면 오류처리
        if(!storedPasssword.equals(password) || !storedEmail.equals(email)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password is wrong");
        }

        // 비밀번호, 이메일이 맞다면, username 바꿀 수 있다.
        findUser.setUsername(username);

        return new UpdateUserResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail(), findUser.getCreatedAt(), findUser.getModifiedAt());
    }

    public void deleteById(Long id, String email, String password) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        String storedPasssword = findUser.getPassword();
        String storedEmail = findUser.getEmail();

        // 비밀번호나 이메일이 틀리다면 오류처리
        if(!storedPasssword.equals(password) || !storedEmail.equals(email)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password is wrong");
        }

        userRepository.delete(findUser);
    }
}
