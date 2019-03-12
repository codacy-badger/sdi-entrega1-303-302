package com.uniovi.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

public class OfferAddFormValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Offer mark = (Offer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		if (mark.getPrice() <= 0) {
			errors.rejectValue("score", "Error.mark.add.score");
		}
	}
}
