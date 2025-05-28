package com.AdmissionManagementSystem.Service;

import com.AdmissionManagementSystem.Entity.Courses;
import com.AdmissionManagementSystem.Entity.EnrollmentForm;
import com.AdmissionManagementSystem.Entity.User;
import com.AdmissionManagementSystem.Repositories.CoursesRepository;
import com.AdmissionManagementSystem.Repositories.EnrollmentRepository;
import com.AdmissionManagementSystem.Repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


//    @Transactional
    public void saveApplication(EnrollmentForm enrollmentForm, String username) {
        try{
            User user = userService.findByUsername(username);
            EnrollmentForm saved = enrollmentRepository.save(enrollmentForm);
            user.getEnrollmentForm().add(saved);
            userService.saveUser(user);
            System.out.println(user.getEmail());
            String to = user.getEmail();
            String subject = "Application Submitted Successfully";
            String text = "Dear " + user.getName() + ",\n\n" +
                    "Your application has been submitted successfully.\n" +
                    "Current Status: " + saved.getStatus() + "\n\n" +
                    "Thank you for applying.\n\n" +
                    "Regards,\nAdmission Team";

            emailService.sendEmail(to, subject, text);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving application",e);
        }
    }

    public List<EnrollmentForm> getAllApplication() {
        return enrollmentRepository.findAll();
    }

    public EnrollmentForm getEnrollmentById(ObjectId enrollmentId) {
        return enrollmentRepository.findById(enrollmentId).orElse(null);
    }

    public void updateEnrollment(EnrollmentForm enrollment) {
        enrollmentRepository.save(enrollment);
    }

    public void addCourse(User matchedUser, ObjectId id) {
        EnrollmentForm enrollmentForm = enrollmentRepository.findById(id).orElse(null);
        if (enrollmentForm == null) {
            throw new RuntimeException("Enrollment form not found");
        }


        String courseCode = enrollmentForm.getCourseCode();
        Courses course = coursesRepository.findByCourseCode(courseCode);

        if (course == null) {
            throw new RuntimeException("Course not found with course code: " + courseCode);
        }

        // Add only if it's not already present
        List<Courses> enrolledCourses = matchedUser.getCourseEnrolled();
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            matchedUser.setCourseEnrolled(enrolledCourses);
            userRepository.save(matchedUser);
        }
    }

}
