package com.cognizant.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.entity.User;
import com.cognizant.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserRepository mockRepository;

	private static final ObjectMapper om = new ObjectMapper();

	@Before
	public void init() {

		User user1 = new User();
		user1.setFirstName("Shrawan");
		user1.setUserId(1L);
		user1.setEmployeeId("101");

		User user2 = new User();
		user2.setFirstName("Shiva");
		user2.setLastName("Kumar");
		user2.setEmployeeId("102");

		List<User> userList = Arrays.asList(user1, user2);
		when(mockRepository.save(any(User.class))).thenReturn(user1);
		when(mockRepository.findAll()).thenReturn(userList);
		/*when(mockRepository.findAll()).thenReturn(userList);
		when(mockRepository.save(any(User.class))).thenReturn(user2);
		doNothing().when(mockRepository).deleteById(1L);*/

	}

	@Test
	public void getUsers() throws Exception {

		mockMvc.perform(get("/api/v1/users")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].employeeId", is("101")))
				.andExpect(jsonPath("$.[0].firstName", is("Shrawan")));

	}
	

}
