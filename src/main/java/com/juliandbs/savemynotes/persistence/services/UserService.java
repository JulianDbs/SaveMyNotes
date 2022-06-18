package com.juliandbs.savemynotes.persistence.services;

import com.juliandbs.savemynotes.registration.dto.UserDto;
import com.juliandbs.savemynotes.main.utils.CustomResponse;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;

public interface UserService {

	//Create
	public CustomResponse createNewUser(Model model, Errors errors, UserDto newUser);

	//Read
	public void getUser(String userEmail);

	//Update
	public void changeUsername(String userEmail, String newUsername);

	public void changePassword(String userEmail, String newPassword);

	//Delete
	public void deleteUser(String userEmail);
}
