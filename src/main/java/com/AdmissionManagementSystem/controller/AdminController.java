package com.AdmissionManagementSystem.controller;

import com.AdmissionManagementSystem.Entity.Courses;
import com.AdmissionManagementSystem.Entity.Student;
import com.AdmissionManagementSystem.Service.AdminService;
import com.AdmissionManagementSystem.Service.CourseService;
import com.AdmissionManagementSystem.Service.StudentService;
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
    private AdminService adminService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    //Crud operation for Students

    @GetMapping("/all-students")
    public ResponseEntity<?> getAllStudenets(){
        List<Student> all=adminService.getAllStudents();
        if (!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudent(@PathVariable ObjectId id){
        try {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                return new ResponseEntity<>(student, HttpStatus.OK);
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

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "Health check form Public Controllere";
    }
}
