function validateFormLogin() {
	clearErrorMsgs();
	var validForm = true;
	validForm = validateNotEmptyInput('username', validForm);
	validForm = validateEmailInput('username', validForm);
	validForm = validateNotEmptyInput('password', validForm);
	return validForm;
}

function validateFormRecuperarSenha() {
	clearErrorMsgs();
	var validForm = true;
	validForm = validateNotEmptyInput('email', validForm);
	validForm = validateEmailInput('email', validForm);
	return validForm;
}

function validateFormResetSenha() {
	clearErrorMsgs();
	var validForm = true;
	validForm = validateNotEmptyInput('senha', validForm);
	validForm = validateNotEmptyInput('confirmaSenha', validForm);
	validForm = validateEqualsInput('senha', 'confirmaSenha', validForm);
	return validForm;
}

function validateFormCadastro() {
	clearErrorMsgs();
	var validForm = true;
	validForm = validateNotEmptyInput('nome', validForm);
	validForm = validateNotEmptyInput('email', validForm);
	validForm = validateEmailInput('email', validForm);
	validForm = validateNotEmptyInput('confirmaEmail', validForm);
	validForm = validateEmailInput('confirmaEmail', validForm);
	validForm = validateEmailEqualsInput('email', 'confirmaEmail', validForm);
	validForm = validateNotEmptyInput('senha', validForm);
	validForm = validateNotEmptyInput('confirmaSenha', validForm);
	validForm = validateEqualsInput('senha', 'confirmaSenha', validForm);
	return validForm;
}
