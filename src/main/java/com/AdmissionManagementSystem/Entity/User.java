package com.AdmissionManagementSystem.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document(collection = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
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

    private List<String> role;


    private List<Courses> courseEnrolled=new ArrayList<>();
    @DBRef
    private List<EnrollmentForm> enrollmentForm=new ArrayList<>();

}
