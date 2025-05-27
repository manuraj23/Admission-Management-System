package com.AdmissionManagementSystem.controller;

import com.AdmissionManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;



    @GetMapping("healthCheck")
    public String healthCheck() {
        return "Health Check from Student Controller";
    }

}
