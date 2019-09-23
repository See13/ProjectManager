package com.cognizant.service;

import java.util.List;

import com.cognizant.entity.ParentTask;
import com.cognizant.entity.Task;
import com.cognizant.model.TaskModel;

public interface TaskService {

	TaskModel addTask(TaskModel task);
	ParentTask addParentTask(Task parentTask);
	List<TaskModel> getTasks();
	List<ParentTask> getParenTasks();
	TaskModel getTaskById(Long taskId);
	TaskModel updateTask(TaskModel model, Long taskId);
	TaskModel endTask(Long taskId);
	
}
