package com.example.schedulerv2.service;

import com.example.schedulerv2.dto.ReadScheduleResponseDto;
import com.example.schedulerv2.dto.SaveScheduleResponseDto;
import com.example.schedulerv2.dto.UpdateScheduleResponseDto;
import com.example.schedulerv2.entity.Schedule;
import com.example.schedulerv2.entity.User;
import com.example.schedulerv2.repository.ScheduleRepository;
import com.example.schedulerv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public SaveScheduleResponseDto save(String title, String contents, String username) {
        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new SaveScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents(), savedSchedule.getCreatedAt(), savedSchedule.getModifiedAt());
    }

    public ReadScheduleResponseDto findById(Long id) {
        log.info("id: {}", id);

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        log.info("findSchedule: {}", findSchedule);

        return new ReadScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getUser().getUsername(), findSchedule.getCreatedAt(), findSchedule.getModifiedAt());
    }

    @Transactional
    public UpdateScheduleResponseDto update(Long id, String email, String password, String title, String contents) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        String storedEmail = findSchedule.getUser().getEmail();
        String storedPassword = findSchedule.getUser().getPassword();

        if(!storedPassword.equals(password) || !storedEmail.equals(email)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password or Email is wrong");
        }

        findSchedule.setTitle(title);
        findSchedule.setContents(contents);

        return new UpdateScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getUser().getUsername(), findSchedule.getCreatedAt(), findSchedule.getModifiedAt());
    }

    public void deleteById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }
}
