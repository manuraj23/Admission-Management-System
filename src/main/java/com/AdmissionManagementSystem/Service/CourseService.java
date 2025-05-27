package com.AdmissionManagementSystem.Service;

import com.AdmissionManagementSystem.Entity.Courses;
import com.AdmissionManagementSystem.Repositories.CoursesRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseService {

    @Autowired
    private CoursesRepository coursesRepository;

    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }

    public void addCourse(Courses course) {
        coursesRepository.save(course);
    }

    public void deleteCourseById(ObjectId id) {
        coursesRepository.deleteById(id);
    }

    public Courses getCourseById(String id) {
        return coursesRepository.findByCourseCode(id);
    }

    public Courses updateCourse(String id, Courses updatedCourse) {
            Courses existingCourse = coursesRepository.findByCourseCode(id);
            if (existingCourse != null) {
                existingCourse.setName(updatedCourse.getName());
                existingCourse.setDescription(updatedCourse.getDescription());
                existingCourse.setDuration(updatedCourse.getDuration());
                existingCourse.setFee(updatedCourse.getFee());
                return coursesRepository.save(existingCourse);
            }
            return null;
    }

}
