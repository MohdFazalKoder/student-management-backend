package com.example.student_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.student_management.model.User;
import com.example.student_management.service.UserAuthService;
import com.example.student_management.dto.LoginRequests;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("users")
public class UserController {
  
    @Autowired
    private UserAuthService userAuthService;
    
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userAuthService.register(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequests loginRequests) {
        return userAuthService.login(loginRequests.getEmail(), loginRequests.getPassword());
    }
}