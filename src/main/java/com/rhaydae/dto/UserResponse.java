package com.rhaydae.dto;

public record UserResponse(
		Long id,
        String username,
        String email,
        String phone
        ) {

}
