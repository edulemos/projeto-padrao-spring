package com.baseproject.shared.constants;

public final class Pages {

	private Pages() {
		throw new IllegalStateException("Constant Pages class");
	}

	public static final String INDEX = "index";
	public static final String LOGIN = "login";

	public static final String ACCOUNT_DATA = "account/account-data";
	public static final String ACCOUNT_PWD = "account/account-pwd";
	
	public static final String CEP = "cep/cep";

	public static final String FUNCIONALIDADES_FORM = "funcionalidades/funcionalidades-form";
	public static final String FUNCIONALIDADES_DELETE = "funcionalidades/funcionalidades-delete";
	public static final String FUNCIONALIDADES_LIST = "funcionalidades/funcionalidades-list";
	public static final String FUNCIONALIDADES_SEARCH = "funcionalidades/funcionalidades-search";

	public static final String PROFILES_FORM = "profiles/profiles-form";
	public static final String PROFILES_DELETE = "profiles/profiles-delete";
	public static final String PROFILES_LIST = "profiles/profiles-list";
	public static final String PROFILES_SEARCH = "profiles/profiles-search";

	public static final String PARAMETROS_FORM = "parametros/parametros-form";
	public static final String PARAMETROS_DELETE = "parametros/parametros-delete";
	public static final String PARAMETROS_LIST = "parametros/parametros-list";
	public static final String PARAMETROS_SEARCH = "parametros/parametros-search";

	public static final String RECOVER = "auth/recover";
	public static final String RECOVER_SAVE = "auth/recover-save";
	public static final String REGISTER = "auth/register";

	public static final String USERS_FORM = "users/users-form";
	public static final String USERS_DELETE = "users/users-delete";
	public static final String USERS_LIST = "users/users-list";
	public static final String USERS_SEARCH = "users/users-search";

}