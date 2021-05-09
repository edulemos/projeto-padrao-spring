package com.baseproject.account.dto;

import javax.validation.constraints.NotBlank;

import com.baseproject.auth.validator.ConfirmPassword;
import com.baseproject.auth.validator.ChangePwd;
import com.baseproject.auth.validator.ValidStrenghtPassword;

import lombok.Data;

@Data
@ChangePwd
public class ChagePwdDto {

	private String uuid;

	@ConfirmPassword
	@NotBlank
	private String oldPassword;
	
	@NotBlank
	@ValidStrenghtPassword
	private String newPassword;

	@NotBlank
	private String newPasswordConfirm;

}
