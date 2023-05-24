package com.example.softwarePractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SoftwarePracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftwarePracticeApplication.class, args);
	}

}
