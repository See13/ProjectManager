package com.cognizant.service;

import java.util.List;

import com.cognizant.entity.User;

public interface UserService {
	User addUser(User user);
	List<User> getUsers();
	User updateUser(User user);
	void deleteUser(Long userId);
}
