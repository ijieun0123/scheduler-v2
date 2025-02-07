package com.example.schedulerv2.repository;

import com.example.schedulerv2.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findScheduleById(Long id);

    default Schedule findScheduleByIdOrElseThrow(Long id){
        return findScheduleById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id: " + id));
    }

    Page<Schedule> findAll(Pageable pageable);

    Page<Schedule> findSchedulesByUserId(Long userId, Pageable pageable);
}
