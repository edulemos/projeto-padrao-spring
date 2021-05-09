package com.baseproject.auth.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.baseproject.auth.dto.RegisterDto;

public class RegisterValidator implements ConstraintValidator<Register, RegisterDto> {

	@Override
	public void initialize(Register p) {
	}

	public boolean isValid(RegisterDto dto, ConstraintValidatorContext c) {
		return dto.getPassword().equals(dto.getPasswordConfirm());
	}

}