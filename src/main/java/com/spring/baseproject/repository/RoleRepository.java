package com.spring.baseproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.baseproject.entity.Role;
import com.spring.baseproject.entity.User;

public interface RoleRepository  extends JpaRepository<Role, Long>  {
    
	List<Role> findRoleByUser(User user);

	
}
