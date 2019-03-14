package com.uniovi.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

@Component
public class OfferEditFormValidator implements Validator {

	private static final Pattern pattern = Pattern.compile("^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))"
			+ "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$, A-Za-z0-9])+)" + "([).!';/?:, ][[:blank:]])?$");
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Offer offer = (Offer) target;
		Matcher matcher = pattern.matcher(offer.getPicture());
		if (offer.getPrice() <= 0) {
			errors.rejectValue("price", "Error.offer.add.price");
		} else if (offer.getDescription() == null || offer.getDescription().length() <= 0) {
			errors.rejectValue("description", "Error.offer.add.description");
		} else if (offer.getTitle() == null || offer.getTitle().length() <= 0) {
			errors.rejectValue("title", "Error.offer.add.title");
		} else if (offer.getPicture() == null || !matcher.find()) {
			errors.rejectValue("picture", "Error.offer.add.picture");
		}
	}
}
