package com.baseproject.auth.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.baseproject.account.dto.ChagePwdDto;

public class ChangePwdValidator implements ConstraintValidator<ChangePwd, ChagePwdDto> {

	@Override
	public void initialize(ChangePwd p) {
	}

	public boolean isValid(ChagePwdDto dto, ConstraintValidatorContext c) {
		return dto.getNewPassword().equals(dto.getNewPasswordConfirm());
	}

}