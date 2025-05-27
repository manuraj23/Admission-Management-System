package com.AdmissionManagementSystem.Repositories;

import com.AdmissionManagementSystem.Entity.Courses;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends MongoRepository<Courses, ObjectId> {
    Courses findByCourseCode(String courseCode);
}
