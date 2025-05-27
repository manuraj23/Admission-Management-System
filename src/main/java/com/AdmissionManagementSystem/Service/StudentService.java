package com.AdmissionManagementSystem.Service;

import com.AdmissionManagementSystem.Entity.Student;
import com.AdmissionManagementSystem.Repositories.StudentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void saveNewStudent(Student student) {
        student.setRole(List.of("STUDENT"));
        studentRepository.save(student);
    }

    public Student getStudentById(ObjectId id) {
        return studentRepository.findById(id).orElse(null);
    }
}
