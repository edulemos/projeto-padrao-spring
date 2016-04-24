package com.spring.baseproject.enumeration;

public enum RolesEnum {

	ADMINISTRADOR("ROLE_ADMIN"), USUARIO("ROLE_USER");
	
	private String value;

	RolesEnum(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}

}
