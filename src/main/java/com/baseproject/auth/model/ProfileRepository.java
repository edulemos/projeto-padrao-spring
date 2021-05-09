package com.baseproject.auth.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	Profile findProfileByName(String nome);

	Optional<Profile> findByUuid(String uuid);

	List<Profile> findByNameContainingIgnoreCase(String name);

}
