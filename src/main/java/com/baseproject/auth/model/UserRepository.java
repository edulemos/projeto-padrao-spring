package com.baseproject.auth.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUuid(String uuid);

	Optional<User> findByUsername(String username);

	List<User> findByNameContainingIgnoreCaseOrderByNameAsc(String name, Pageable pageable);

	List<User> findByUsernameContainingIgnoreCaseOrderByUsernameAsc(String name, Pageable pageable);

	List<User> findByOrderByNameAsc();

	Boolean existsByUsername(String username);

	Boolean existsByRecoverUuid(String uuid);

	User findByRecoverUuid(String username);

	@Query("SELECT user FROM User user WHERE ?1 MEMBER OF user.profiles")
	List<User> findByProfile(Profile profile);

}
