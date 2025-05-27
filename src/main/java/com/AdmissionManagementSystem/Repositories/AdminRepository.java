package com.AdmissionManagementSystem.Repositories;
import com.AdmissionManagementSystem.Entity.Admin;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, ObjectId> {
    Admin findByUsername(String username);

    void deleteByUsername(String username);
}
