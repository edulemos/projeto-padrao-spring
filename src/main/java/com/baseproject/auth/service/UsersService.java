package com.baseproject.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baseproject.auth.model.Profile;
import com.baseproject.auth.model.ProfileRepository;
import com.baseproject.auth.model.User;
import com.baseproject.auth.model.UserRepository;
import com.baseproject.util.PageableUtil;
import com.baseproject.util.ServiceUtil;

@Service
@Transactional
public class UsersService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User find(String uuid) {
		User u = null;

		if (!uuid.isEmpty()) {
			u = repository.findByUuid(uuid).orElse(new User());
		} else {
			u = new User();
		}

		List<Profile> all = profileRepository.findAll();
		List<Profile> avaliable = new ArrayList<>();

		for (Profile p : all) {
			boolean found = u.getProfiles().stream().anyMatch(p1 -> p1.getId().equals(p.getId()));
			if (!found) {
				avaliable.add(p);
			}
		}

		u.setAvaliableProfiles(avaliable);

		return u;
	}

	public void delete(String uuid) {
		User userDB = repository.findByUuid(uuid).orElseThrow();
		repository.delete(userDB);
	}

	public void save(User user) {
		if (ServiceUtil.notEmpty(user.getUuid())) {
			User userDB = repository.findByUuid(user.getUuid()).orElseThrow();
			userDB.setName(user.getName());
			userDB.setTel(user.getTel());
			userDB.setProfiles(user.getProfiles());
			userDB.setIsAccountNonLocked(user.getIsAccountNonLocked());
			repository.save(userDB);
		} else {
			user.setUuid(ServiceUtil.generateUuid());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setIsAccountNonLocked(true);
			repository.save(user);
		}
	}

	public List<User> list(Optional<String> fieldName, Optional<String> fieldValue) {
		if (!fieldName.isPresent() || !fieldValue.isPresent()) {
			return repository.findAll(PageableUtil.getPageable("name")).toList();
		} else if (fieldName.get().equals("name")) {
			return repository.findByNameContainingIgnoreCaseOrderByNameAsc(fieldValue.get().trim(), PageableUtil.getPageable());
		} else if (fieldName.get().equals("username")) {
			return repository.findByUsernameContainingIgnoreCaseOrderByUsernameAsc(fieldValue.get().trim(), PageableUtil.getPageable());
		} else {
			return repository.findAll(PageableUtil.getPageable("name")).toList();
		}
	}

	public boolean checkEmailDuplicate(Optional<String> uuidOpt, Optional<String> emailOpt) {

		if (!emailOpt.isPresent()) {
			return false;
		} else {
			Optional<User> userOpt = repository.findByUsername(emailOpt.get());

			if (!userOpt.isPresent()) {
				return false;
			} else {
				User user = userOpt.get();
				String uuid = uuidOpt.isPresent() ? uuidOpt.get() : "";
				if (uuid.isEmpty() || !user.getUuid().equals(uuid)) {
					return true;
				} else {
					return false;
				}

			}

		}

	}

}
