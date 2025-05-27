package com.AdmissionManagementSystem.Service;

import com.AdmissionManagementSystem.Entity.Student;
import com.AdmissionManagementSystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminService {

    @Autowired

    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


}
