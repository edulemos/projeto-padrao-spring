/**MENSAGENS DE ERRO PADRONIZADAS**/

var notEmptyMessage = "<span class='input-erro span-erro'>Voc\u00ea n\u00e3o pode deixar este campo em branco.</span>";
var notEqualsMessage = "<span class='input-erro span-erro'>Valor diferente da confirma\u00e7\u00e3o</span>";
var emailMessage = "<span class='input-erro span-erro'>Email inv\u00e1lido</span>";

/**METODOS UTILITARIOS**/

//retorna true se o email for valido
function validateEmail(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

function validateNotEmptyInput(inputName, validForm) {
	var test = validForm;
	var selector = 'input[name=' + inputName + ']';
	var testValue = $(selector).val();
	if (testValue.length < 1) {
		test = false;
		$(notEmptyMessage).insertAfter(selector);
		$(selector).addClass('error');
	} else {
		$(selector).removeClass('error');
	}
	return test;
}

function validateNotEmptySelect(inputName, validForm) {
	var test = validForm;
	var selector = 'select[name="' + inputName + '"] option:selected';
	var selector2 = 'select[name="' + inputName + '"]';
	var testValue = $(selector).val();
	if (testValue.length < 1) {
		test = false;
		$(notEmptyMessage).insertAfter(selector2);		
		$(selector2).addClass('error');
	} else {
		$(selector2).removeClass('error');
	}
	return test;
}

function validateEmailInput(inputName, validForm) {
	var test = validForm;
	var selector = 'input[name=' + inputName + ']';
	var testValue = $(selector).val();
	if (testValue.length > 0 && !validateEmail(testValue)) {
		test = false;
		$(emailMessage).insertAfter(selector);
		$(selector).addClass('error');
	}else if (testValue.length > 0 && validateEmail(testValue)) {
		$(selector).removeClass('error');
	}
	return test;
}

function validateEqualsInput(inputName1, inputName2, validForm) {
	var test = validForm;

	var selector1 = 'input[name=' + inputName1 + ']';
	var testValue1 = $(selector1).val();

	var selector2 = 'input[name=' + inputName2 + ']';
	var testValue2 = $(selector2).val();

	if ((testValue1.length > 0 && testValue2.length > 0) && (testValue1 != testValue2)) {
		test = false;
		$(notEqualsMessage).insertAfter(selector1);
		$(selector1).addClass('error');
	}else if ((testValue1.length > 0 && testValue2.length > 0) && (testValue1 == testValue2)) {	
		$(selector1).removeClass('error');
	}

	return test;
}

function validateEmailEqualsInput(inputName1, inputName2, validForm) {
	var test = validForm;

	var selector1 = 'input[name=' + inputName1 + ']';
	var testValue1 = $(selector1).val();

	var selector2 = 'input[name=' + inputName2 + ']';
	var testValue2 = $(selector2).val();

	if ((testValue1.length > 0 && testValue2.length > 0) && (validateEmail(testValue1) && validateEmail(testValue2)) && (testValue1 != testValue2)) {
		test = false;
		$(notEqualsMessage).insertAfter(selector1);
		$(selector1).addClass('error');
	} else if ((testValue1.length > 0 && testValue2.length > 0) && (validateEmail(testValue1) && validateEmail(testValue2)) && (testValue1 == testValue2)) {
		$(selector1).removeClass('error');
	}

	return test;
}

function clearErrorMsgs() {
	$(".span-erro").empty();
}
