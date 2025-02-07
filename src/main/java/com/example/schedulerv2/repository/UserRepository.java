package com.example.schedulerv2.repository;

import com.example.schedulerv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    default User findUserByIdOrElseThrow(Long id){
        return findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id: " + id));
    }

    Optional<User> findUserByEmail(String email);

    default User findUserByEmailOrElseThrow(String email){
        return findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists email: " + email));
    }

    default User findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id: " + id));
    }
}
