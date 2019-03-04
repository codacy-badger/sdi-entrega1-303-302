package com.uniovi.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

public class LoginFormValidator implements Validator {

	@Autowired
	private UsersService usersService;
	
	// Patrón para validar el email
    Pattern pattern = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		Matcher matcher = pattern.matcher(user.getEmail());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");
		if (!matcher.find()) {
			errors.rejectValue("email", "Error.login.email.syntax");
		}
		if (usersService.getUserByEmail(user.getEmail()) == null) {
			errors.rejectValue("email", "Error.login.email.doesNotExist");
		}
		if (usersService.getUserByEmail(user.getEmail()).getPassword().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Error.login.passwordConfirm.notCorrect");
		}
	}
}
