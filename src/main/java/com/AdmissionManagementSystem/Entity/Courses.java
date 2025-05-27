package com.AdmissionManagementSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Courses {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String courseCode;

    @NonNull
    private String name;

    private String description;

    private String duration;

    private String fee;


}
