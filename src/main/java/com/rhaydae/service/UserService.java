package com.rhaydae.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rhaydae.dto.UserRequest;
import com.rhaydae.dto.UserResponse;
import com.rhaydae.entity.User;
import com.rhaydae.mapper.UserMapper;
import com.rhaydae.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserResponse saveUser(UserRequest dto) {
		User user = UserMapper.toEntity(dto);
		User savedUser = userRepository.save(user);
		
		return UserMapper.toResponse(savedUser);
	}
	
	public UserResponse getUser(Long id) {
		return userRepository.findById(id)
				.map(UserMapper :: toResponse)
				.orElseThrow(() -> new RuntimeException("user id not found"));
	}
	
	public List<UserResponse> getAllUsers(){
		return userRepository.findAll()
				.stream()
				.map(UserMapper :: toResponse)
				.toList();
		}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	public UserResponse updateUser(Long id, UserRequest dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());

        return UserMapper.toResponse(userRepository.save(user));
    }
	
	
	public Optional<User> findByUsername(String name) {
		return userRepository.findByUsername(name);
	}
	
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
