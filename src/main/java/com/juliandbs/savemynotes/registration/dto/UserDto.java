package com.juliandbs.savemynotes.registration.dto;

import com.juliandbs.savemynotes.registration.annotations.ValidUsername;
import com.juliandbs.savemynotes.registration.annotations.ValidUsernameCharacters;
import com.juliandbs.savemynotes.registration.annotations.ValidUsernameSize;
import com.juliandbs.savemynotes.registration.annotations.ValidEmail;
import com.juliandbs.savemynotes.registration.annotations.ValidEmailPattern;
import com.juliandbs.savemynotes.registration.annotations.PasswordMatches;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@PasswordMatches()
public class UserDto {

	@ValidUsername
	@ValidUsernameCharacters
	@ValidUsernameSize
	private String username;

	@ValidEmail
	@ValidEmailPattern
	private String email;

	@NotNull
	@NotEmpty(message="Email field is empty")
	@Size(message="Password length must be greater than 4 and less than 20", min=5, max=21)
	private String password;

	@NotNull
	@Size(message="Password length must be greater than 4 and less than 20", min=5, max=21)
	@NotEmpty(message="Password field is empty")

	private String matchingPassword;

	public String getUsername() {return username;}

	public void setUsername(final String username) {this.username = username;}

	public String getEmail() {return email;}

	public void setEmail(final String email) {this.email = email;}

	public String getPassword() {return password;}

	public void setPassword(final String password) {this.password = password;}

	public String getMatchingPassword() {return matchingPassword;}

	public void setMatchingPassword(final String matchingPassword) {this.matchingPassword = matchingPassword;}

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
