package com.AdmissionManagementSystem.controller;

import com.AdmissionManagementSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("healthCheck")
    public String healthCheck() {
        return "Health Check from Student Controller";
    }

}
