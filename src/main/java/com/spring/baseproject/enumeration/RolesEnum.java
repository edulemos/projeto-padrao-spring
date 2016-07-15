package com.spring.baseproject.enumeration;

public enum RolesEnum {

	MANTER_USUARIOS(1, "ROLE_MANTER_USUARIOS", "hasRole('MANTER_USUARIOS')", "/admin/user/**", "Manter usuários sistema"),
	MANTER_PERFIS(3,"ROLE_MANTER_PERFIS", "hasRole('MANTER_PERFIS')", "/admin/perfil/**", "Manter perfis sistema"),
	MANTER_PRODUTOS(2, "ROLE_MANTER_PRODUTOS", "hasRole('MANTER_PRODUTOS')", "/cadastros/produto/**", "Manter produtos"); 

	private Integer id;
	private String role;
	private String roleAcess;
	private String url;
	private String descricao;

	RolesEnum(Integer id, String role, String roleAcess, String url, String descricao) {
		this.id = id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
