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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public SaveScheduleResponseDto save(String email, String title, String contents) {
        User findUser = userRepository.findUserByEmailOrElseThrow(email);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new SaveScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents(), savedSchedule.getCreatedAt(), savedSchedule.getModifiedAt());
    }

    public ReadScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ReadScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getUser().getUsername(), findSchedule.getCreatedAt(), findSchedule.getModifiedAt());
    }

    public List<ReadScheduleResponseDto> findAll() {
        List<Schedule> findSchedules = scheduleRepository.findAll();

        return findSchedules.stream().map(findSchedule -> {
            return new ReadScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getUser().getUsername(), findSchedule.getCreatedAt(), findSchedule.getModifiedAt());
        }).collect(Collectors.toList());
    }

    @Transactional
    public UpdateScheduleResponseDto update(Long id, String title, String contents) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        if(title != null) findSchedule.setTitle(title);
        if(contents != null) findSchedule.setContents(contents);

        return new UpdateScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getUser().getUsername(), findSchedule.getCreatedAt(), findSchedule.getModifiedAt());
    }

    public void deleteById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }
}
