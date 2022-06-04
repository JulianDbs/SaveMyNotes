package com.juliandbs.savemynotes.registration.controllers;

import com.juliandbs.savemynotes.registration.dto.UserDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {

	@GetMapping("/registration")
        public String getRegistration(Model model) {
                model.addAttribute("registryForm", new UserDto() );
                return "registry/registry";
        }

	@PostMapping("/registration")
	public String registerUserAccount(
			@ModelAttribute("registryForm")
			@Valid final UserDto userDto,
			final BindingResult bindingResult,
			final HttpServletRequest request,
			final Errors errors
	) {
		System.out.println("Acount : (" + userDto.toString() + ")");
		System.out.println("Errors count : (" + errors.getErrorCount() + ")");

		String toUrl = "login/login";
		if (errors.hasErrors()) {
			toUrl = "registry/registry";
		}
		return toUrl;
	}
}
