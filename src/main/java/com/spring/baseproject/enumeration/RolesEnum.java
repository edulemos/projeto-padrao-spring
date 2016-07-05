package com.spring.baseproject.enumeration;

public enum RolesEnum {

	ADMIN_MANTER_USUARIOS("ROLE_ADMIN_MANTER_USUARIOS", "hasRole('ADMIN_MANTER_USUARIOS')", "/admin/user/**", "Manter usuários sistema"),
	CADASTRO_PRODUTO("ROLE_CADASTRO_PRODUTO", "hasRole('CADASTRO_PRODUTO')", "/cadastros/produto/**", "Manter produtos"),
	CONTA_USUARIO("ROLE_CONTA_USUARIO", "hasRole('CONTA_USUARIO')", "/user/**", "Manter Conta usuário");

	private String role;
	private String roleAcess;
	private String url;
	private String descricao;

	RolesEnum(String role, String roleAcess, String url, String descricao) {
		this.role = role;
		this.roleAcess = roleAcess;
		this.url = url;
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public String getRole() {
		return role;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getRoleAcess() {
		return roleAcess;
	}

}
