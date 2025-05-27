package com.AdmissionManagementSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String username;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String mobile;

    @NonNull
    private String password;

    @NonNull
    private List<String> role;

//    @DBRef
    private List<Courses> course;

//    @DBRef
    private List<String> EnrollmentStatus;

}
