package com.rhaydae.mapper;

import com.rhaydae.dto.UserRequest;
import com.rhaydae.dto.UserResponse;
import com.rhaydae.entity.User;

public class UserMapper {

	public static User toEntity(UserRequest dto) {
		return User.builder()
				.username(dto.username())
				.email(dto.email())
				.password(dto.password())
				.phone(dto.phone())
				.build();
	}
	
	
	public static UserResponse toResponse(User user) {
		return new UserResponse(
				user.getId(), user.getUsername(), user.getEmail(), user.getPhone());
	}
}
