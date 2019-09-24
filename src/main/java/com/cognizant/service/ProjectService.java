package com.cognizant.service;

import java.util.List;
import java.util.Set;

import com.cognizant.entity.Project;
import com.cognizant.entity.Task;

public interface ProjectService {
	Project addProject(Project project);
	List<Project> getProjects();
	Set<Task> getTasksByProject(Long projectId);
	
}
