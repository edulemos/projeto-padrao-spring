package com.baseproject.parameterization.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.baseproject.parameterization.model.Funcionalidade;
import com.baseproject.parameterization.model.FuncionalidadeRepository;

@Component
public class FuncionalidadeDeleteValidator implements Validator {

	@Autowired
	FuncionalidadeRepository repository;

	@Override
	public boolean supports(Class<?> clazz) {
		return String.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		String uuid = (String) target;
		
		Funcionalidade f = repository.findByUuid(uuid).orElseThrow();

		if (null != f.getParametros() && !f.getParametros().isEmpty()) {
			errors.rejectValue("name", "funcionalidade.erro.parametro");
		}

	}

}
