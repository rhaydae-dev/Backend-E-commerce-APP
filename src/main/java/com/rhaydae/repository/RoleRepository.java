package com.rhaydae.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhaydae.entity.Role;
import com.rhaydae.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
    Optional<Role> findByName(RoleName name);

}
