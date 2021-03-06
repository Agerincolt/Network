package it.uniroma3.siw.nw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.nw.model.Credentials;
import it.uniroma3.siw.nw.service.CredentialsService;


@Component
public class CredentialsValidator implements Validator {
	
	@Autowired
	CredentialsService credentialsService;
	
	final Integer MAX_USERNAME_LENGTH = 20;
	final Integer MIN_USERNAME_LENGHT = 4;
	final Integer MAX_PASSWORD_LENGTH = 20;
	final Integer MIN_PASSWORD_LENGHT = 6;
	
	@Override
	public void validate(Object o, Errors errors) {
		Credentials credentials = (Credentials) o;
		String username = credentials.getUsername().trim();
		String password = credentials.getPassword().trim();
		
		if(username.isEmpty())
			errors.rejectValue("username", "required");
		else if (username.length() < MIN_USERNAME_LENGHT || username.length() > MAX_USERNAME_LENGTH)
			errors.rejectValue("username", "size");
		else if (this.credentialsService.getCredentials(username)!=null)
			errors.rejectValue("username", "duplicate");
		else if (this.credentialsService.getCredentials(username)==null)
			errors.rejectValue("username", "invalid");
		
		if(password.isEmpty())
			errors.rejectValue("lastName", "required");
		else if (password.length() < MIN_PASSWORD_LENGHT || password.length() > MAX_PASSWORD_LENGTH)
			errors.rejectValue("lastName", "size");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Credentials.class.equals(clazz);
	}
}