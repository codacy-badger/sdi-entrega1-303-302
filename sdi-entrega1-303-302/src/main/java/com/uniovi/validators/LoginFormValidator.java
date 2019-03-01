package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

public class LoginFormValidator implements Validator {

	@Autowired
	private UsersService usersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		if (user.getDni().length() < 5 || user.getDni().length() > 24) {
			errors.rejectValue("dni", "Error.login.dni.length");
		}
		if (usersService.getUserByDni(user.getDni()) == null) {
			errors.rejectValue("dni", "Error.login.dni.doesNotExist");
		}
		if (usersService.getUserByDni(user.getDni()).getPassword().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Error.login.passwordConfirm.notCorrect");
		}
	}
}
