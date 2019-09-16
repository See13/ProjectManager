package com.learn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectManagerApplication {
	private static final Logger logger = LogManager.getLogger(ProjectManagerApplication.class);
	public static void main(String[] args) {
		logger.info("Enter into main method in ProjectManagerApplication.......");
		SpringApplication.run(ProjectManagerApplication.class, args);
		logger.info("Exit from main method in ProjectManagerApplication.......");
	
	}

}
