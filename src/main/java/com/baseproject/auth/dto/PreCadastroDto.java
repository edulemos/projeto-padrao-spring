package com.baseproject.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreCadastroDto {
	
	@NotBlank
	private String nome;

	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String perfil;
}
