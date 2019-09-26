package com.cognizant.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.entity.ParentTask;
import com.cognizant.entity.Task;
import com.cognizant.model.TaskModel;
import com.cognizant.service.TaskService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class TaskController {

	@Autowired
	TaskService taskService;
	private static final Logger logger = LogManager.getLogger(TaskController.class);

	@PostMapping("/addTask")
	public ResponseEntity<TaskModel> createTask(@RequestBody TaskModel taskModel) {
		logger.info("Enter into createTask method in controller......."+taskModel.getTaskName());

		return new ResponseEntity<TaskModel>(this.taskService.addTask(taskModel), HttpStatus.CREATED);
	}

	@PostMapping("/addParent")
	public ResponseEntity<ParentTask> createParentTask(@RequestBody Task task) {
		logger.info("Enter into createParentTask method in controller.......");

		return new ResponseEntity<ParentTask>(this.taskService.addParentTask(task), HttpStatus.CREATED);
	}

	@GetMapping("/tasks")
	public ResponseEntity<List<TaskModel>> getTasks() {
		logger.info("Enter into getTasks method in controller.......");
		List<TaskModel> taskList = taskService.getTasks();

		return new ResponseEntity<List<TaskModel>>(taskList, HttpStatus.OK);
	}

	@GetMapping("/parentTasks")
	public ResponseEntity<List<ParentTask>> getParenTasks() {
		logger.info("Enter into getParenTasks method in controller.......");
		List<ParentTask> taskList = taskService.getParenTasks();

		return new ResponseEntity<List<ParentTask>>(taskList, HttpStatus.OK);
	}

	@GetMapping("/task/{taskId}")
	public ResponseEntity<TaskModel> getTaskById(@PathVariable("taskId") long taskId) {
		logger.info("Enter into getTaskById method in controller.......");
		TaskModel task = taskService.getTaskById(taskId);

		return new ResponseEntity<TaskModel>(task, HttpStatus.OK);
	}

	@PutMapping("/task/{taskId}")
	public TaskModel updateTask(@RequestBody TaskModel task, @PathVariable("taskId") long taskId) {
		logger.info("Enter into updateTask method in controller.......");
		return taskService.updateTask(task, taskId);
	}

	@PutMapping("/endTask/{taskId}")
	public TaskModel endTask(@PathVariable("taskId") long taskId) {
		logger.info("Enter into endTask method in controller.......");
		TaskModel task= taskService.endTask(taskId);
		 return taskService.updateTask(task, taskId);
	}

}
