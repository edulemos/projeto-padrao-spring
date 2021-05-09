package com.baseproject.auth.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

public class PasswordStrengthValidator implements ConstraintValidator<ValidStrenghtPassword, String> {

	@Override
	public void initialize(ValidStrenghtPassword arg0) {
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {

		List<Rule> rules = new ArrayList<>();

		rules.add(new LengthRule(5, 15)); // de 6 a 10 caracteres
		rules.add(new WhitespaceRule()); // sem espacos
		rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1)); // uma letra maiuscula
		rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1)); // uma minuscula
		rules.add(new CharacterRule(EnglishCharacterData.Digit, 1)); // um numero

		PasswordValidator validator = new PasswordValidator(rules);
		RuleResult result = validator.validate(new PasswordData(password));
		
		return result.isValid();
	}
}