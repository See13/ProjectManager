package com.cognizant.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.entity.ParentTask;
import com.cognizant.entity.Task;
import com.cognizant.model.TaskModel;
import com.cognizant.repository.ParentTaskRepository;
import com.cognizant.repository.TaskRepository;
import com.cognizant.service.TaskService;

public class TaskServiceImpl implements TaskService {
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	ParentTaskRepository parentTaskRepository;

	@Override
	public TaskModel addTask(TaskModel model) {
		Task task = getTaskFromModel(model);
		TaskModel returnModel = getModelFromTask(this.taskRepository.save(task));
		return returnModel;
	}

	@Override
	public ParentTask addParentTask(Task parentTask) {
		ParentTask parent = new ParentTask();
		parent.setParentTask(parentTask.getTaskName());
		return this.parentTaskRepository.save(parent);
	}

	@Override
	public List<TaskModel> getTasks() {
		List<TaskModel> taskModelList = new ArrayList<TaskModel>();
		List<Task> tasks = this.taskRepository.findAll();
		for (Task task : tasks) {
			TaskModel model = getModelFromTask(task);
			taskModelList.add(model);
		}
		return taskModelList;
	}

	@Override
	public List<ParentTask> getParenTasks() {
		List<ParentTask> parentList = new ArrayList<ParentTask>();
		parentList = this.parentTaskRepository.findAll();
		return parentList;
	}

	@Override
	public TaskModel getTaskById(Long taskId) {
		Optional<Task> task = this.taskRepository.findById(taskId);
		TaskModel taskModel = getModelFromTask(task.get());
		return taskModel;
	}

	@Override
	public TaskModel updateTask(TaskModel model, Long taskId) {
		Optional<Task> task = this.taskRepository.findById(taskId);

		task.get().setParent(model.getParent());
		task.get().setProject(model.getProject());
		task.get().setUser(model.getUser());
		task.get().setTaskName(model.getTaskName());
		task.get().setPriority(model.getPriority());
		task.get().setStatus(model.getStatus());
		task.get().setStartDate(model.getStartDate());
		task.get().setEndDate(model.getEndDate());

		TaskModel taskModel = getModelFromTask(task.get());
		return taskModel;
	}

	@Override
	public TaskModel endTask(Long taskId) {
		Optional<Task> task = this.taskRepository.findById(taskId);

		task.get().setStatus("1");
		task.get().setEndDate(LocalDate.now());

		TaskModel taskModel = getModelFromTask(task.get());
		return taskModel;
	}

	Task getTaskFromModel(TaskModel model) {
		Task task = new Task();

		task.setTaskId(model.getTaskId());
		task.setParent(model.getParent());
		task.setProject(model.getProject());
		task.setUser(model.getUser());
		task.setPriority(model.getPriority());
		task.setStatus(model.getStatus());
		task.setStartDate(model.getStartDate());
		task.setEndDate(model.getEndDate());
		task.setTaskName(model.getTaskName());

		return task;
	}

	TaskModel getModelFromTask(Task task) {
		TaskModel taskModel = new TaskModel();

		taskModel.setTaskId(task.getTaskId());
		taskModel.setParent(task.getParent());
		taskModel.setProject(task.getProject());
		taskModel.setUser(task.getUser());
		taskModel.setPriority(task.getPriority());
		taskModel.setStatus(task.getStatus());
		taskModel.setStartDate(task.getStartDate());
		taskModel.setEndDate(task.getEndDate());
		taskModel.setTaskName(task.getTaskName());

		return taskModel;
	}
}
