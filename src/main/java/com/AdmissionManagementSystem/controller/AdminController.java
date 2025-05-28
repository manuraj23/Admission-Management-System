package com.AdmissionManagementSystem.controller;

import com.AdmissionManagementSystem.Entity.Courses;
import com.AdmissionManagementSystem.Entity.EnrollmentForm;
import com.AdmissionManagementSystem.Entity.User;
import com.AdmissionManagementSystem.Service.EmailService;
import com.AdmissionManagementSystem.Service.EnrollmentService;
import com.AdmissionManagementSystem.Service.UserService;
import com.AdmissionManagementSystem.Service.CourseService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private EmailService emailService;


    @GetMapping("/all-students")
    public ResponseEntity<?> getAllStudenets(){
        List<User> all= userService.getAllStudents();
        if (!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudent(@PathVariable ObjectId id){
        try {
            User user = userService.getStudentById(id);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/all-courses")
    public ResponseEntity<?> getAllCourses(){
        List<Courses>all=courseService.getAllCourses();
        if (!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable String id){
        try {
            Courses course = courseService.getCourseById(id);
            if (course != null) {
                return new ResponseEntity<>(course, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addCourse")
    public ResponseEntity<?> addCourse(@RequestBody Courses course){
        try{
            courseService.addCourse(course);
            return new ResponseEntity<>(course, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @RequestBody Courses updatedCourse) {
        try {
            Courses updated = courseService.updateCourse(id, updatedCourse);
            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable ObjectId id) {
        try {
            courseService.deleteCourseById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getAllEnrollment")
    public ResponseEntity<?> getAllApplications(){
        List<EnrollmentForm>all=enrollmentService.getAllApplication();
        if (!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateEnrollmentStatus/{id}")
    public ResponseEntity<?> updateEnrollmentStatus(@PathVariable ObjectId id, @RequestBody EnrollmentForm enrollmentForm) {
        try {
            EnrollmentForm enrollment = enrollmentService.getEnrollmentById(id);

            User matchedUser1 = null;
            List<User> allUsers = userService.getAllStudents();
            for (User u : allUsers) {
                if (u.getEnrollmentForm() != null) {
                    for (EnrollmentForm ef : u.getEnrollmentForm()) {
                        if (ef.getId().equals(id)) {
                            matchedUser1 = u;
                            break;
                        }
                    }
                }
                if (matchedUser1 != null) break;
            }

            System.out.println(enrollment);
            System.out.println(matchedUser1);

            String to = matchedUser1.getEmail();
            String subject = "Application " + enrollmentForm.getStatus();
            String text = "Dear " + matchedUser1.getName() + ",\n\n" +
                    "Your application has been"+ enrollmentForm.getStatus() +
                    "Thank you for applying.\n\n" +
                    "Regards,\nAdmission Team";

            emailService.sendEmail(to, subject, text);

            if ("APPROVED".equalsIgnoreCase(String.valueOf(enrollmentForm.getStatus()))) {
                enrollmentService.addCourse(matchedUser1,id);
            }

            if (enrollment != null){
                enrollment.setStatus(enrollmentForm.getStatus());
                enrollmentService.updateEnrollment(enrollment);
                return new ResponseEntity<>(enrollment, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "Health check form Admin Controllere";
    }
}
