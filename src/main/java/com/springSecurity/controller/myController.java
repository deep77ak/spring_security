package com.springSecurity.controller;

import com.springSecurity.employee.employee;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class myController {
    public List<employee> list = new ArrayList<>(List.of(new employee(101, "deepak", 28)
            , new employee(102, "Sunny", 29)));


    @GetMapping("/home")
    public String Test() {
        return "spring security test";

    }

    // @GetMapping("/csrf")
    // public CsrfToken srftoken(HttpServletRequest request) {
    //     return (CsrfToken) request.getAttribute("_csrf");
    // }

    @GetMapping("/employee")
    public List<employee> getemp() {
        return list;
    }

    @PostMapping("/new employee")
    public String addemp(@RequestBody employee emp) {
        list.add(emp);
        return "employee added successfully";


    }
}
