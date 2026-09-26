package com.rhaydae.security;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rhaydae.entity.Role;
import com.rhaydae.enums.RoleName;
import com.rhaydae.repository.RoleRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct // 🔥 S’exécute automatiquement après le démarrage de Spring
    public void initRoles() {
        // Si les rôles existent déjà, on ne les recrée pas
        if (roleRepository.count() == 0) {
            Role adminRole = Role.builder()
                    .name(RoleName.ADMIN)
                    .build();

            Role userRole = Role.builder()
                    .name(RoleName.USER)
                    .build();

            roleRepository.saveAll(List.of(adminRole, userRole));
            System.out.println("✅ Roles ADMIN and USER initialized successfully!");
        } else {
            System.out.println("ℹ️ Roles already exist — skipping initialization.");
        }
    }
}