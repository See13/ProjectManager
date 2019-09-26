package com.cognizant.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.entity.ParentTask;
import com.cognizant.entity.Project;
import com.cognizant.entity.Task;
import com.cognizant.entity.User;
import com.cognizant.repository.ParentTaskRepository;
import com.cognizant.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TaskControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskRepository mockRepository;
	
	@MockBean
	private ParentTaskRepository mockParent;

	private static final ObjectMapper om = new ObjectMapper();
	
	@Before
	public void init() {
		ParentTask parent1 = new ParentTask();
		parent1.setParentTaskId(11L);
		parent1.setParentTask("Parent11");
		
		ParentTask parent2 = new ParentTask();
		parent2.setParentTaskId(2L);
		parent2.setParentTask("Parent2");
		
		
		Task task = new Task();
	    User user2 = new User();
		user2.setFirstName("Seema");
		user2.setLastName("Yadav");
		user2.setEmployeeId("104");
	
		Project prj1 = new Project();
		prj1.setProjectName("TestProject 1");
		prj1.setProjectId(1L);
		
		task.setTaskId(5L);
		task.setParent(parent1);
		task.setTaskName("task1");
		task.setUser(user2);
		task.setProject(prj1);
		
		Task task1 = new Task();
		task1.setTaskId(6L);
		task1.setParent(parent2);
		task1.setTaskName("task2");
		task1.setUser(user2);
		task1.setProject(prj1);
		
		List<Task> taskList = Arrays.asList(task, task1);
		
		
		
		
		List<ParentTask> parentList = Arrays.asList(parent1, parent2);
		when(mockRepository.findById(5L)).thenReturn(Optional.of(task));
		when(mockParent.findAll()).thenReturn(parentList);
		when(mockParent.save(any(ParentTask.class))).thenReturn(parent2);
		when(mockRepository.save(any(Task.class))).thenReturn(task);
		when(mockRepository.findAll()).thenReturn(taskList);
	}
	
	
	@Test 
	public void getParenTasks() throws Exception {
	mockMvc.perform(get("/api/v1/parentTasks")).andExpect(status().isOk())
	.andExpect(jsonPath("$.[0].parentTaskId", is(11)))
	 .andExpect(jsonPath("$.[0].parentTask", is("Parent11")));
	 
	 
	 }
	
	 @Test
	 public void createParentTask() throws Exception {

		 ParentTask parentTask = new ParentTask();
		 parentTask.setParentTaskId(2L);
		 parentTask.setParentTask("Parent2");
			mockMvc.perform(post("/api/v1/addParent").content(om.writeValueAsString(parentTask)).header(HttpHeaders.CONTENT_TYPE,
					MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpect(jsonPath("$.parentTask", is("Parent2")))
					.andExpect(jsonPath("$.parentTaskId", is(2)));
		
	 }
	
	 @Test
	 public void createTask() throws Exception {

		 Task task = new Task();
		 User user2 = new User();
			user2.setFirstName("Shrawan");
			user2.setLastName("Kumar");
			user2.setEmployeeId("102");
		
			Project prj1 = new Project();
			prj1.setProjectName("TestProject 1");
			prj1.setProjectId(1L);
			
			ParentTask parent1 = new ParentTask();
			parent1.setParentTaskId(1L);
			parent1.setParentTask("Parent1");
			task.setTaskId(5L);
			task.setParent(parent1);
			task.setTaskName("task1");
			task.setUser(user2);
			task.setProject(prj1);
			
			mockMvc.perform(post("/api/v1/addTask").content(om.writeValueAsString(task)).header(HttpHeaders.CONTENT_TYPE,
					MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpect(jsonPath("$.taskName", is("task1")))
					.andExpect(jsonPath("$.taskId", is(5)));
					
		
	 }
	 
	 @Test 
	 public void getTasks() throws Exception {
	 mockMvc.perform(get("/api/v1/tasks")).andExpect(status().isOk())
	 .andExpect(jsonPath("$.[1].taskId", is(6)))
	  .andExpect(jsonPath("$.[1].taskName", is("task2")));
	  
	  
	  }
	 
	 @Test
	 public void endTask() throws Exception {
		 Task task = new Task();
		    User user2 = new User();
			user2.setFirstName("Seema");
			user2.setLastName("Yadav");
			user2.setEmployeeId("123");
		
			Project prj1 = new Project();
			prj1.setProjectName("TestProject 1");
			prj1.setProjectId(1L);
			
			ParentTask parent1 = new ParentTask();
			parent1.setParentTaskId(1L);
			parent1.setParentTask("Parent1");
			
			task.setTaskId(5L);
			task.setParent(parent1);
			task.setTaskName("task1");
			task.setUser(user2);
			task.setProject(prj1);
		 mockMvc.perform(put("/api/v1/endTask/5").content(om.writeValueAsString(task)).header(HttpHeaders.CONTENT_TYPE,
					MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.taskId", is(5)))
					.andExpect(jsonPath("$.taskName", is("task1")));
	 }
	 
	 @Test
	 public void updateTask() throws Exception {

		 Task task = new Task();
		 User user2 = new User();
			user2.setFirstName("Shivansh");
			user2.setLastName("Yadav");
			user2.setEmployeeId("102");
		
			Project prj1 = new Project();
			prj1.setProjectName("TestProject 1");
			prj1.setProjectId(1L);
			
			ParentTask parent1 = new ParentTask();
			parent1.setParentTaskId(1L);
			parent1.setParentTask("Parent1");
			task.setTaskId(5L);
			task.setParent(parent1);
			task.setTaskName("task1");
			task.setUser(user2);
			task.setProject(prj1);
			
			mockMvc.perform(put("/api/v1/task/5").content(om.writeValueAsString(task)).header(HttpHeaders.CONTENT_TYPE,
					MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.taskName", is("task1")))
					.andExpect(jsonPath("$.taskId", is(5)));
					
		
	 }
	 
	 @Test
	 public void getTaskById() throws Exception {

		 mockMvc.perform(get("/api/v1/task/5")).andExpect(status().isOk())
		 .andExpect(jsonPath("$.taskId", is(5)))
		  .andExpect(jsonPath("$.taskName", is("task1")));
		  
	 }
		  
		 
	 }