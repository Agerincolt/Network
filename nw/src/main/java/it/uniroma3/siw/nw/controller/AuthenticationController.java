package it.uniroma3.siw.nw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

import it.uniroma3.siw.nw.model.Credentials;
import it.uniroma3.siw.nw.model.Customer;
import it.uniroma3.siw.nw.service.CredentialsService;
import it.uniroma3.siw.nw.controller.validation.CredentialsValidator;
import it.uniroma3.siw.nw.controller.validation.CustomerValidator;

@Controller
public class AuthenticationController {

	@Autowired
	CredentialsService credentialsService;

	@Autowired
	CustomerValidator customerValidator;

	@Autowired
	CredentialsValidator credentialsValidator;

	public AuthenticationController() {

	}

	/**
	 * This method is called when a GET request is sent by the user to URL "/register".
	 * This method prepares and dispatches the User registration view.
	 * 
	 * @param model the Request model
	 * @return the name of the target view, that in this case is "registerUser"
	 */
	@RequestMapping(value = {"/users/register"}, method = RequestMethod.GET)
	public String showRegisterForm(Model model) {
		model.addAttribute("userForm", new Customer()); //viene aggiunto al modello uno User vuoto
		model.addAttribute("credentialsForm", new Credentials()); //viene aggiunto al modello un Credentials vuoto
		return "registerUser";
	}

	@RequestMapping(value = {"/users/register"}, method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("userForm") Customer customer,
			BindingResult userBindingResult,
			@Valid @ModelAttribute("credentialsForm") Credentials credentials,
			BindingResult credentialsBindingResult,
			Model model) {

		//validate user and credentials fields
		this.customerValidator.validate(customer, userBindingResult);
		this.credentialsValidator.validate(credentials, credentialsBindingResult);

		//if neither of them had invalid contents, store the User and the Credentials into the DB
		if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
			//set the user and store the credentials;
			//this also stores the Customer, thanks to Cascade.ALL policy
			credentials.setCustomer(customer);
			credentialsService.saveCredentials(credentials);
			return "registrationSuccessful";
		}
		return "registerCustomer";
	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String ShowLogin(Model model) {
		return "login";
	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public String Login(@Valid @ModelAttribute("userForm") Customer customer,
			BindingResult userBindingResult,
			@Valid @ModelAttribute("credentialsForm") Credentials credentials,
			BindingResult credentialsBindingResult,
			Model model) {

		//validate user and credentials fields
		this.customerValidator.validate(customer, userBindingResult);
		this.credentialsValidator.validate(credentials, credentialsBindingResult);

		//if neither of them had invalid contents, store the User and the Credentials into the DB
		if(userBindingResult.hasErrors() || credentialsBindingResult.hasErrors())
			return "login";
		else
			return "home";
	}
}

