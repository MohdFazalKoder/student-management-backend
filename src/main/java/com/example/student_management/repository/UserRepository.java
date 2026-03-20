package com.example.student_management.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.student_management.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
