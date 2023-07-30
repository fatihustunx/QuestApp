package com.project.questapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.User;
import com.project.questapp.exceptions.UserNotFoundException;
import com.project.questapp.responses.UserResponse;
import com.project.questapp.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	public User createOneUser(@RequestBody User newUser) {
		return userService.createOneUser(newUser);
	}

	@GetMapping("/{userId}")
	public UserResponse getOneUser(@PathVariable Long userId) {
		// custom exception !
		User user = userService.getOneUserById(userId);
		if(user == null) {
			throw new UserNotFoundException();
		}
		return new UserResponse(user);
	}

	@PutMapping("/{userId}")
	public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
		return userService.updateOneUserById(userId, newUser);
	}

	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable Long userId) {
		this.userService.deleteOneUserById(userId);
	}
	
	@GetMapping("/activity/{userId}")
	public List<Object> getUserActivity(@PathVariable Long userId){
		return userService.getUserActivity(userId);
	}
	
}