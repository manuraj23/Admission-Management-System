package com.AdmissionManagementSystem.Repositories;

import com.AdmissionManagementSystem.Entity.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, ObjectId> {
}
