package com.cognizant.controller;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.entity.Project;
import com.cognizant.entity.Task;
import com.cognizant.service.ProjectService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	private static final Logger logger = LogManager.getLogger(ProjectController.class);

	@PostMapping("/addProject")
	public ResponseEntity<Project> createProject(@RequestBody Project project) {
		logger.info("Enter into createProject method in controller.......");

		return new ResponseEntity<Project>(projectService.addProject(project), HttpStatus.CREATED);
	}

	@GetMapping("/projects")
	public ResponseEntity<List<Project>> getProjects() {
		logger.info("Enter into getProjects method in controller.......");

		return new ResponseEntity<List<Project>>(projectService.getProjects(), HttpStatus.OK);
	}

	@GetMapping("/projects/{projectId}/tasks")
	Set<Task> getTasksByProject(@PathVariable("projectId") Long projectId) {
		logger.info("Enter into getTasksByProject method in controller.......");
		return projectService.getTasksByProject(projectId);
	}
}
