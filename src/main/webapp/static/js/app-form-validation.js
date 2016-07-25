function validateFormAdminUser(id) {
	clearErrorMsgs();
	var validForm = true;
	
	validForm = validateNotEmptyInput('name', validForm);	
	validForm = validateNotEmptyInput('email', validForm);
	validForm = validateEmailInput('email', validForm);
	
	if (typeof id == 'undefined'){		
		validForm = validateNotEmptyInput('password', validForm);
		validForm = validateNotEmptySelect('perfis[0].id', validForm);
	}
		
	return validForm;
}

function validateFormUser() {
	clearErrorMsgs();
	var validForm = true;
	
	validForm = validateNotEmptyInput('name', validForm);	
	validForm = validateNotEmptyInput('email', validForm);
	validForm = validateEmailInput('email', validForm);
	
	return validForm;
}

function validateFormSenhaUser() {
	clearErrorMsgs();
	var validForm = true;
	
	validForm = validateNotEmptyInput('senha', validForm);
	validForm = validateNotEmptyInput('novaSenha', validForm);
	validForm = validateNotEmptyInput('confirmaSenha', validForm);
	validForm = validateEqualsInput('novaSenha', 'confirmaSenha', validForm);
	
	return validForm;
}
