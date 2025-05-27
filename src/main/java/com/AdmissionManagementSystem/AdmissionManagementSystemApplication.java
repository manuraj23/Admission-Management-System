package com.AdmissionManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class AdmissionManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdmissionManagementSystemApplication.class, args);
	}

}
