package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AdvancedJavaFinalAssignmentBackendApplication {

	public static void main(String[] args) {
	 	ConfigurableApplicationContext run = SpringApplication.run(AdvancedJavaFinalAssignmentBackendApplication.class, args);
		//UserService userService = run.getBean(UserService.class);
	}

}
