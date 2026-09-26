package com.rhaydae.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
		
		@NotBlank(message = "Username is required")
	    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
		String username,
		
		@NotBlank(message = "Email is required")
	    @Email(message = "Invalid email format")
		String email,
		
		@NotBlank(message = "Password is required")
	    @Size(min = 8, max = 50, message = "Password must be at least 8 characters")
		//You can add a regex for strong passwords:
   /*  @Pattern(
	            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
	            message = "Password must contain at least one uppercase letter, one lowercase letter, and one number"
	           )
   */
		String password,
		
		@NotBlank(message = "Phone number is required")
	    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must contain only digits (10–15 digits)")
		String phone,
		
		List<String> roles
		) 

{}
