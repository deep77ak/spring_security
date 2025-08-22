package com.springSecurity.service;

import com.springSecurity.entity.employeeSignup;
import com.springSecurity.repo.EmployeeSignupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class customUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeSignupRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        employeeSignup emp = repo.findByUsername(username);
        if (emp == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return new User(emp.getUsername(), emp.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
