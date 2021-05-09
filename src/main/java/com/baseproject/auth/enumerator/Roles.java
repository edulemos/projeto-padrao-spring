package com.baseproject.auth.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {

	ROLE_PROFILES_LIST("/profiles", "consultar perfis"), 
	ROLE_PROFILES_SAVE("/profiles-save", "criar ou editar perfis"),
	ROLE_PROFILES_DELETE("/profiles-delete", "excluir perfis"),
	
	ROLE_USERS_LIST("/users", "consultar usuarios"),
	ROLE_USERS_SAVE("/users-save", "criar ou editar usuarios"),
	ROLE_USERS_DELETE("/users-delete", "excluir usuarios"),
	
	ROLE_PARAMETER_LIST("/parametros", "consultar parametros"),
	ROLE_PARAMETER_SAVE("/parametros-save", "criar ou editar parametros"),
	ROLE_PARAMETER_DELETE("/parametros-delete", "excluir parametros"),
	
	ROLE_FUNCIONALIDADE_LIST("/funcionalidades", "consultar funcionalidades"),
	ROLE_FUNCIONALIDADE_SAVE("/funcionalidades-save", "criar ou editar funcionalidades"),
	ROLE_FUNCIONALIDADE_DELETE("/funcionalidades-delete", "excluir funcionalidades");
	
	private String url;
	private String description;

	public static Roles find(String role) {
		for (Roles r : values()) {
			if (r.toString().equals(role)) {
				return r;
			}
		}
		return null;
	}
	
}
