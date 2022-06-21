package com.juliandbs.savemynotes.persistence.services;

import com.juliandbs.savemynotes.registration.dto.UserDto;
import com.juliandbs.savemynotes.persistence.services.UserService;
import com.juliandbs.savemynotes.persistence.models.User;
import com.juliandbs.savemynotes.persistence.repositories.UserRepository;
import com.juliandbs.savemynotes.persistence.exceptions.UserNotFoundException;
import com.juliandbs.savemynotes.persistence.exceptions.UserAlreadyExistsException;
import com.juliandbs.savemynotes.persistence.exceptions.PasswordNotValidException;
import com.juliandbs.savemynotes.main.utils.ValidationErrorFilter;
import com.juliandbs.savemynotes.main.utils.CustomResponse;
import com.juliandbs.savemynotes.persistence.repositories.UserInfoRepository;
import com.juliandbs.savemynotes.persistence.models.UserInfo;
import com.juliandbs.savemynotes.persistence.exceptions.UserInfoNotFoundException;
import com.juliandbs.savemynotes.persistence.exceptions.UserInfoAlreadyExistsException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.LinkedList;

@Service("userService")
@EnableJpaRepositories("com.juliandbs.savemynotes.persistence.repositories")
public class UserServiceImpl implements UserService {

	@Autowired
	ValidationErrorFilter validationErrorFilter;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	//Create
	@Override
	public CustomResponse createNewUser(Model model, Errors errors, UserDto newUser) {
		String toUrl = "login/login";
		if (errors.hasErrors()) {
                        toUrl = "registration/registration";
                        model.addAttribute("usernameErrors", validationErrorFilter.getUsernameErrors(errors));
                        model.addAttribute("emailErrors", validationErrorFilter.getEmailErrors(errors));
                        model.addAttribute("passwordErrors", validationErrorFilter.getPasswordErrors(errors));
                        model.addAttribute("matchingPasswordErrors", validationErrorFilter.getMatchingPasswordErrors(errors));
                } else {
			User user = new User(
					newUser.getUsername(),
					newUser.getEmail(),
					bCryptPasswordEncoder.encode(newUser.getPassword())
			);
			UserInfo userInfo = new UserInfo(newUser.getEmail(), Long.valueOf(0));
			try {
				userRepository.addNewUser(user);
				//userInfoRepository.addNewUserInfo(userInfo);
				userInfoRepository.addNewUserInfo(newUser.getEmail(), Long.valueOf(0));
	                        model.addAttribute("name", user.getUsername());
	                        toUrl="registration/success_registration";
			} catch (UserAlreadyExistsException | UserInfoAlreadyExistsException e) {
				List<String> errorList = new LinkedList<>();
				errorList.add("User Already Exists");
				model.addAttribute("emailErrors", errorList);
	                        toUrl = "registration/registration";
			}
                }
		return new CustomResponse(toUrl, model);
	}

	//Read
        public void getUser(String userEmail) {}

        //Update
        public void changeUsername(String userEmail, String newUsername) {}

        public void changePassword(String userEmail, String newPassword) {}

        //Delete
        public void deleteUser(String userEmail) {}

}
