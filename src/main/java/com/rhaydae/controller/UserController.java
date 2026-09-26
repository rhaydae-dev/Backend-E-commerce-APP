package com.rhaydae.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rhaydae.dto.UserRequest;
import com.rhaydae.dto.UserResponse;
import com.rhaydae.entity.Product;
import com.rhaydae.entity.User;
import com.rhaydae.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping("/saveUser")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public UserResponse saveUser(@RequestBody UserRequest user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/getUser/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserResponse getUser(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@GetMapping("/getAllUsers")
	@ResponseStatus(HttpStatus.OK)
	public List<UserResponse> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
	}
	
	@GetMapping("/searchUsername")
	public Optional<User> searchByUsername(@RequestParam String userName) {
		return userService.findByUsername(userName);
	}
	
	@GetMapping("/searchEmail")
	public Optional<User> searchByEmail(@RequestParam String email) {
		return userService.findByEmail(email);
	}
}
