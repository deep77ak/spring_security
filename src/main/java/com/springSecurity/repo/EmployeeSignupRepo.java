package com.springSecurity.repo;

import com.springSecurity.entity.employeeSignup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSignupRepo extends JpaRepository<employeeSignup,Integer> {
    employeeSignup findByUsername(String username);
}
