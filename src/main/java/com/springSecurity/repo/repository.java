package com.springSecurity.repo;

import com.springSecurity.entity.employeeSignup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface repository extends JpaRepository<employeeSignup, Integer> {
    Optional<employeeSignup> findByUsername(String username);
}
