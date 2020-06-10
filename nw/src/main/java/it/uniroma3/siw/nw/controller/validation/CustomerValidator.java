package it.uniroma3.siw.nw.controller.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.nw.model.Customer;

@Component
public class CustomerValidator implements Validator {
	
	final Integer MAX_NAME_LENGTH = 100;
	final Integer MIN_NAME_LENGHT = 2;
	
	@Override
	public void validate(Object o, Errors errors) {
		Customer customer = (Customer) o;
		String name = customer.getName().trim();
		String surname = customer.getSurname().trim();
		
		if(name.isEmpty())
			errors.rejectValue("name", "required");
		else if (name.length() < MIN_NAME_LENGHT || name.length() > MAX_NAME_LENGTH)
			errors.rejectValue("name", "size");
		
		if(surname.isEmpty())
			errors.rejectValue("surname", "required");
		else if (surname.length() < MIN_NAME_LENGHT || name.length() > MAX_NAME_LENGTH)
			errors.rejectValue("surname", "size");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.equals(clazz);
	}
}