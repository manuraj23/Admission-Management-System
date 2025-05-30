package com.AdmissionManagementSystem.Entity;

import com.AdmissionManagementSystem.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "EnrollmentForm")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentForm {

    @Id
    private ObjectId id;

    @NonNull
    private String courseCode;

    @NonNull
    private String twelfthMarks;

    private Status status;

}
