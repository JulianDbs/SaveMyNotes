package com.juliandbs.savemynotes.registration.dto;

import com.juliandbs.savemynotes.registration.annotations.ValidUsername;
import com.juliandbs.savemynotes.registration.annotations.ValidUsernameCharacters;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class UserDto {

	@ValidUsername
	@ValidUsernameCharacters
	@NotEmpty(message="Username field is empty")
	@Size(message="Username length must be greater than 4 and less than 20", min=4, max=21)
	private String username;

	@NotNull
	@NotEmpty(message="Email field is empty")
	@Size(message="Email length must be greater than 4 and less than 20", min=5, max=21)
	private String email;

	@NotNull
	@NotEmpty(message="Email field is empty")
	@Size(message="Password length must be greater than 4 and less than 20", min=5, max=21)
	private String password;

	@NotNull
	@Size(message="Email length must be greater than 4 and less than 20", min=5, max=21)
	@NotEmpty(message="Username field is empty")

	private String matchingPassword;

	public String getUsername() {return username;}

	public void setUsername(String username) {this.username = username;}

	public String getEmail() {return email;}

	public void setEmail() {this.email = email;}

	public String getPassword() {return password;}

	public void setPassword(String password) {this.password = password;}

	public String getMatchingPassword() {return matchingPassword;}

	public void setMatchingPassword(String matchingPassword) {this.matchingPassword = matchingPassword;}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("Registry(");
		stringBuilder.append("username: " + username + ",");
		stringBuilder.append("email: " + email + ",");
		stringBuilder.append("password: " + password + ",");
		stringBuilder.append("matchingPassword: " + matchingPassword + ")");
		return stringBuilder.toString();
	}
}
