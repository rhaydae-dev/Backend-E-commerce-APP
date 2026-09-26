package com.rhaydae.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rhaydae.dto.AuthRequest;
import com.rhaydae.dto.AuthResponse;
import com.rhaydae.dto.RegisterRequest;
import com.rhaydae.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
		return authService.register(request);
	}
	
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public AuthResponse login( @Valid @RequestBody AuthRequest request) {
		return authService.authenticate(request);
	}

}
