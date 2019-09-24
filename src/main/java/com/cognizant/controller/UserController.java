package com.cognizant.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.entity.User;
import com.cognizant.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	UserService userService;
	private static final Logger logger = LogManager.getLogger(UserController.class);

	@PostMapping("/addUser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		logger.info("Enter into createUser method in controller.......");

		return new ResponseEntity<User>(this.userService.addUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		logger.info("Enter into getUsers method in controller.......");
		List<User> userList = userService.getUsers();

		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		logger.info("Enter into updateUser method in controller.......");
		return userService.updateUser(user);
	}

	@DeleteMapping("/user/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		logger.info("Enter into deleteUser method in controller.......");
		userService.deleteUser(userId);
	}
}
