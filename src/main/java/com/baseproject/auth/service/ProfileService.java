package com.baseproject.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baseproject.auth.model.Profile;
import com.baseproject.auth.model.ProfileRepository;
import com.baseproject.auth.model.Role;
import com.baseproject.auth.model.RoleRepository;
import com.baseproject.util.PageableUtil;
import com.baseproject.util.ServiceUtil;

@Service
@Transactional
public class ProfileService {

	@Autowired
	private ProfileRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	public Profile find(String uuid) {

		Profile p = null;

		if (!uuid.isEmpty()) {
			p = repository.findByUuid(uuid).orElse(new Profile());
		} else {
			p = new Profile();
		}

		List<Role> allRoles = roleRepository.findAll();
		List<Role> avaliableRoles = new ArrayList<>();

		for (Role role : allRoles) {
			boolean found = p.getRoles().stream().anyMatch(r -> r.getId().equals(role.getId()));
			if (!found) {
				avaliableRoles.add(role);
			}
		}

		p.setAvaliableRoles(avaliableRoles);

		return p;
	}

	public void delete(String uuid) {
		Profile entity = repository.findByUuid(uuid).orElseThrow();
		repository.delete(entity);
	}

	public void save(Profile prm) {
		if (ServiceUtil.notEmpty(prm.getUuid())) {
			Profile entity = repository.findByUuid(prm.getUuid()).orElseThrow();
			entity.setName(prm.getName());
			entity.setRoles(prm.getRoles());
			entity.setDescription(prm.getDescription());
			repository.save(entity);
		} else {
			prm.setUuid(ServiceUtil.generateUuid());
			repository.save(prm);
		}
	}

	public List<Profile> list(Optional<String> fieldName, Optional<String> fieldValue) {
		if (!fieldName.isPresent() || !fieldValue.isPresent()) {
			return repository.findAll(PageableUtil.getPageable("name")).toList();
		} else if (fieldName.get().equals("name")) {
			return repository.findByNameContainingIgnoreCase(fieldValue.get().trim());
		} else {
			return repository.findAll(PageableUtil.getPageable("name")).toList();
		}
	}

}
