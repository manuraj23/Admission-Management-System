﻿# Admission-Management-System

## Overview
The Admission Management System is a RESTful API backend built to streamline and digitize the student admission process for educational institutions. This system provides a comprehensive suite of features including student registration, course management, and admission workflows. It supports role-based access for students and admins, enabling secure login, course enrollment, and admission status tracking. Designed with scalability and maintainability in mind, the system follows clean architecture principles and is containerized with Docker for easy deployment. This project is an ideal foundation for extending functionalities such as notifications, search, and real-time updates.
## Technologies Used
- Java
- Spring Boot
- Spring Security (RBAC)
- Spring Mail
- Maven
- MongoDB
- Docker
- Docker Compose

## Prerequisites
- Java 17 or higher
- Maven
- MongoDB
- Docker Desktop(Running)
- Docker Compose (included with Docker Desktop)

## Database Schema
The system uses MongoDB as the database. The main collections include:
- **students**: Stores student information including name, email, password, and role.
- **courses**: Contains course details such as course name, description, and duration.
- **enrollments**: Manages student enrollment applications, linking students to courses and tracking application status.

  ![MongoDB Database Schema Diagram](https://github.com/user-attachments/assets/d81e896f-0986-4171-a1ab-c1b0f7abf9ec)




# How to Run This Project

## Prerequisites
- Docker Desktop (running)
- Docker Compose (included with Docker Desktop)

## Docker Support
This project includes a Dockerfile and a docker-compose.yml file for easy deployment. To run the application using Docker, follow these steps:
1. You can use Docker Compose to start the application along with MongoDB:
   ```bash
   docker-compose up --build
     ```
2. Alternatively, you can build the Docker image and run it manually:
   ```bash
   docker build -t admission-management-system .
   ```
3. Run the Docker container:
   ```bash
    docker run -p 8085:8085 admission-management-system
    ```
4. The application will be accessible at [http://localhost:8085/AdmissionApp](http://localhost:8085/AdmissionApp).




## Running the Application withoud docker
1. Clone the repository:
   ```bash
   git clone https://github.com/manuraj23/Admission-Management-System
   cd Admission-Management-System
   ```
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. The application will be accessible at [http://localhost:8085/AdmissionApp](http://localhost:8081/journal).

## API Endpoints

## Public Endpoints
- `POST /public/signup` - Register a new student.
- `POST /public/login` - Authenticate a student or admin user.

### User/Student Endpoints
- `GET /student/enrollmentStatus` - Check the enrollment status.
- `POST /student/applyAdmission` - Apply for admission to a course.
- `GET /student/courses` - Retrieve all available courses.


### Admin Endpoints
- `GET /admin/getAllEnrollment` - Retrieve all student enrollment applications.
- `POST /admin/updateEnrollmentStatus/{id}` - Update the status of a student's enrollment application.
- `GET /admin/all-students` - Retrieve all registered students.
- `GET /admin/student/{id}` - Retrieve details of a specific student.
- `GET /admin/all-courses` - Retrieve all available courses.
- `GET /admin/course/{id}` - Retrieve details of a specific course.
- `POST /admin/addCourse` - Add a new course.
- `PUT /admin/updateCourse/{id}` - Update an existing course.

## Role-Based Access Control (RBAC)
- **Admin**: Full access to manage Students, Course and Admissions.
- **User**: Can see Courses, Apply for courses.


## EmailService
Sends email notifications to Students on applying Courses and status Update. For example, If student apply for a course he will get a mail, after sucessful approval/rejection by admin he will get a mail regarding same.


 
   
## Features
- **Student Registration**: Students can register and create accounts.
- **Course Management**: Admins can add, update, and delete courses.
- **Admission Workflow**: Students can apply for courses, and admins can manage applications.
- **Role-Based Access Control**: Secure access for students and admins.
- **Email Notifications**: Students receive email notifications for application status updates.
- **Docker Support**: Easy deployment with Docker and Docker Compose.
- **Scalability**: Designed to handle a growing number of students and courses.
- **Testing**: Includes unit tests for core functionalities to ensure reliability.
- **Documentation**: Comprehensive API documentation for easy integration and usage.
- **Security**: Implements secure authentication and authorization mechanisms.
- **Analytics**: Provides insights into student enrollment and course popularity.
- **Logging**: Integrated logging for monitoring and debugging purposes.
- **Version Control**: Managed with Git for collaborative development and version tracking.
- **Continuous Integration/Continuous Deployment (CI/CD)**: Can be integrated with CI/CD pipelines for automated testing and deployment.
## Contributing
1. Fork the repository.
2. Create a new feature branch.
3. Commit your changes.
4. Push to your fork and submit a pull request.

## License
This project is licensed under the MIT License.

## Contact
For queries, reach out to[Manu Raj](mailto:manuraj082004@gmail.com).
