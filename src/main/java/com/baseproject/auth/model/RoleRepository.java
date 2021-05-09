package com.baseproject.auth.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baseproject.auth.enumerator.Roles;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(Roles roleName);

}
