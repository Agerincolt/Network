package it.uniroma3.siw.nw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	public MainController() {
		
	}
	
	/**
	 * This method is called when a GET request is sent by the user to URL "/" or "/index".
	 * This method prepares and dispatches the index view.
	 * 
	 * @param model the Request model
	 * @return the name of the target view, that in this case is "index"
	 */
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}
}