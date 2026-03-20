package com.example.student_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.student_management.repository.UserRepository;
import com.example.student_management.model.User;
import com.example.student_management.security.JwtUtil;

@Service
public class UserAuthService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder encodePassword = new BCryptPasswordEncoder();

    public User register(User user) {
        user.setPassword(encodePassword.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String login(String email, String  password) {
        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new RuntimeException("User not found");
        }
        if(!encodePassword.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return jwtUtil.generateToken(email);
    }
}
