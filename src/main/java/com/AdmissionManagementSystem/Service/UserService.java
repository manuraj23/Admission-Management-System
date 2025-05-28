package com.AdmissionManagementSystem.Service;

import com.AdmissionManagementSystem.Entity.User;
import com.AdmissionManagementSystem.Repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public void saveNewStudent(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(List.of("STUDENT"));
        userRepository.save(user);
    }

    public User getStudentById(ObjectId id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllStudents() {
        return userRepository.findAll();
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
