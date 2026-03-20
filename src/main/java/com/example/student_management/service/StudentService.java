package com.example.student_management.service;

import org.springframework.stereotype.Service;
import com.example.student_management.repository.StudentRepository;


import com.example.student_management.model.Student;
import java.util.List;

@Service
public class StudentService {
    public  StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student viewStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student saveStudent(Student student) {
        if(student.getName().isEmpty() || student.getEmail().isEmpty() || student.getCourse().isEmpty()) {
            throw new IllegalArgumentException("All fields are required");
        }
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if(existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setCourse(student.getCourse());
            return studentRepository.save(existingStudent);
        }
        return null;
    }

    public  void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}
