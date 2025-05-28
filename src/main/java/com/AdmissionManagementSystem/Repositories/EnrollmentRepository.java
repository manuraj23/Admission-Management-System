package com.AdmissionManagementSystem.Repositories;

import com.AdmissionManagementSystem.Entity.EnrollmentForm;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnrollmentRepository extends MongoRepository<EnrollmentForm, ObjectId> {
}
