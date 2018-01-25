package com.ta.toko.module.user.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ta.toko.module.user.model.UserProfile;

@Component
public class UserProfileValidator implements Validator {

	private static Logger logger = LoggerFactory.getLogger(UserProfileValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return UserProfile.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserProfile validate = (UserProfile) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userProfile.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.userProfile.confirmPassword");

		logger.info("Password: {}, Confirm Password: {}", validate.getPassword(), validate.getConfirmPassword());
		if (isNotEmpty(validate) && isNotEqual(validate)) {
			logger.info("password and confirm passoword didn't match");
			errors.rejectValue("password", "NotEqual.userProfile.password");
			errors.rejectValue("confirmPassword", "NotEqual.userProfile.password");
		}

	}

	private boolean isNotEqual(UserProfile validate) {
		return !validate.getPassword().equals(validate.getConfirmPassword());
	}

	private boolean isNotEmpty(UserProfile validate) {
		return !validate.getPassword().isEmpty() && !validate.getConfirmPassword().isEmpty();
	}

}
