package com.example.schedulerv2.service;

import com.example.schedulerv2.dto.response.ScheduleResponseDto;
import com.example.schedulerv2.entity.Schedule;
import com.example.schedulerv2.entity.User;
import com.example.schedulerv2.repository.ScheduleRepository;
import com.example.schedulerv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public ScheduleResponseDto save(String email, String title, String contents) {
        User findUser = userRepository.findUserByEmailOrElseThrow(email);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleResponseDto.toScheduleDto(savedSchedule);
    }
    public ScheduleResponseDto findById(Long scheduleId) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        return ScheduleResponseDto.toScheduleDto(findSchedule);
    }
    public List<ScheduleResponseDto> findByUserId(Long userId, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        Page<Schedule> commentPage = scheduleRepository.findSchedulesByUserId(userId, pageable);

        return commentPage.getContent().stream().map(ScheduleResponseDto::toScheduleDto).collect(Collectors.toList());
    }

    public List<ScheduleResponseDto> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        Page<Schedule> schedulePage = scheduleRepository.findAll(pageable);

        return schedulePage.getContent().stream().map(ScheduleResponseDto::toScheduleDto).collect(Collectors.toList());
    }

    @Transactional
    public ScheduleResponseDto update(Long id, String title, String contents) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        if(title != null) findSchedule.setTitle(title);
        if(contents != null) findSchedule.setContents(contents);

        return ScheduleResponseDto.toScheduleDto(findSchedule);
    }

    public void deleteById(Long id) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }
}
