package com.cognizant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cognizant.entity.ParentTask;
import com.cognizant.repository.ParentTaskRepository;

@SpringBootApplication
public class ProjectManagerApplication implements CommandLineRunner {

	private static final Logger logger = LogManager.getLogger(ProjectManagerApplication.class);
	@Autowired
	private ParentTaskRepository parentTaskRepository;

	public static void main(String[] args) {
		logger.info("Enter into main method in ProjectManagerApplication.......");
		SpringApplication.run(ProjectManagerApplication.class, args);
		logger.info("Exit from main method in ProjectManagerApplication.......");

	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Enter into run method in TaskManagerApplication.......");

		parentTaskRepository.save(new ParentTask(1, "ParentTask1"));
		parentTaskRepository.save(new ParentTask(2, "ParentTask2"));
		logger.info("Exit from run method in TaskManagerApplication.......");
	}

}
