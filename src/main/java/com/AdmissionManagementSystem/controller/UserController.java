package com.AdmissionManagementSystem.controller;

import com.AdmissionManagementSystem.Entity.Courses;
import com.AdmissionManagementSystem.Entity.EnrollmentForm;
import com.AdmissionManagementSystem.Entity.User;
import com.AdmissionManagementSystem.Service.CourseService;
import com.AdmissionManagementSystem.Service.EnrollmentService;
import com.AdmissionManagementSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("view-all-courses")
    public ResponseEntity<?> getAllCourses(){
        List<Courses> all=courseService.getAllCourses();
        if (!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/applyAdmission")
    public ResponseEntity<?> enrollment(@RequestBody EnrollmentForm enrollmentForm) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            System.out.println(enrollmentForm);
            enrollmentForm.setStatus(com.AdmissionManagementSystem.Enum.Status.PENDING);
            enrollmentService.saveApplication(enrollmentForm,username);
            return new ResponseEntity<>(enrollmentForm, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("enrollmentStatus")
    public ResponseEntity<?> getEnrollmentStatus(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<EnrollmentForm> application=user.getEnrollmentForm();
        if (!application.isEmpty()){
            return new ResponseEntity<>(application, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("healthCheck")
    public String healthCheck() {
        return "Health Check from Student Controller";
    }

}
