package com.AdmissionManagementSystem.controller;

import com.AdmissionManagementSystem.Entity.Student;
import com.AdmissionManagementSystem.Repositories.StudentRepository;
import com.AdmissionManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private StudentService studentService;

    @PostMapping("signup")
    public void signup(@RequestBody Student student){
        studentService.saveNewStudent(student);
    }


}
