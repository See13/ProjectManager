package com.cognizant.serviceimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.entity.Project;
import com.cognizant.entity.Task;
import com.cognizant.entity.User;
import com.cognizant.repository.ProjectRepository;
import com.cognizant.repository.UserRepository;
import com.cognizant.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Project addProject(Project project) {
		projectRepository.save(project);
		User user = project.getUser();
		userRepository.save(user);
		return project;
	}

	@Override
	public List<Project> getProjects() {
		return projectRepository.findAll();
	}

	@Override
	public Set<Task> getTasksByProject(Long projectId) {
		Set<Task> tasks = new HashSet<Task>();
		Project project = projectRepository.findById(projectId).get();
		tasks = project.getTaskList();
		return tasks;
	}

}
