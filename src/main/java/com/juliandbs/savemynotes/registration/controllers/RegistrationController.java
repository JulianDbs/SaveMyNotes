package com.juliandbs.savemynotes.registration.controllers;

import com.juliandbs.savemynotes.persistence.services.UserServiceImpl;
import com.juliandbs.savemynotes.registration.dto.UserDto;
//import com.juliandbs.savemynotes.main.utils.ValidationErrorFilter;
import com.juliandbs.savemynotes.main.utils.CustomResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;

@Controller
public class RegistrationController {

	//@Autowired
	//private ValidationErrorFilter validationErrorFilter;

	@Autowired
	UserServiceImpl userService;

	@GetMapping("/registration")
        public String getRegistration(Model model) {
		UserDto user = new UserDto();
                model.addAttribute("user", user);
                return "registration/registration";
        }

	@PostMapping("/registration")
	public String registerUserAccount(
			@Valid
			@ModelAttribute("user")
			UserDto user,
			Errors errors,
			Model model
	){
		System.out.println("Acount : (" + user.toString() + ")");
		/*
		String toUrl = "login/login";
		if (errors.hasErrors()) {
			toUrl = "registration/registration";
			model.addAttribute("usernameErrors", validationErrorFilter.getUsernameErrors(errors));
			model.addAttribute("emailErrors", validationErrorFilter.getEmailErrors(errors));
			model.addAttribute("passwordErrors", validationErrorFilter.getPasswordErrors(errors));
			model.addAttribute("matchingPasswordErrors", validationErrorFilter.getMatchingPasswordErrors(errors));
		} else {
			model.addAttribute("name", user.getUsername());
			toUrl="registration/success_registration";
		}
		*/
		CustomResponse customResponse = userService.createNewUser(model, errors, user);
		model = customResponse.getModel();
		return customResponse.getUrl();
	}
}
