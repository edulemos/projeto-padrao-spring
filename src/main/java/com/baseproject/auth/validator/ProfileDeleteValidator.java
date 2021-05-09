package com.baseproject.auth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.baseproject.auth.model.Profile;
import com.baseproject.auth.model.ProfileRepository;
import com.baseproject.auth.model.UserRepository;

@Component
public class ProfileDeleteValidator implements Validator {

	@Autowired
	ProfileRepository repository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return String.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		String uuid = (String) target;
		
		Profile p = repository.findByUuid(uuid).orElseThrow();

		if (null != p.getRoles() && !p.getRoles().isEmpty()) {
			errors.rejectValue("name", "profile.erro.delete");
		}else if(!userRepository.findByProfile(p).isEmpty()) {
			errors.rejectValue("name", "profile.erro.delete.user");
		}

	}

}
