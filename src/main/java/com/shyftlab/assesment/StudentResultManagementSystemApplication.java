package com.shyftlab.assesment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//EntityScan(basePackages = "com.shyftlab.assesment.entities")
public class StudentResultManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentResultManagementSystemApplication.class, args);
	}

}
