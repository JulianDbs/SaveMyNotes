package com.juliandbs.savemynotes.persistence.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity(name = "users")
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;

	@Column(name = "username", nullable = false, length = 21)
	private String username;

	@Column(name = "email", nullable = false, length = 40, unique = true)
	private String email;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	public User() {}

	public User(String username, String email, String password) throws NullPointerException {
		if (username == null || email == null || password == null)
			throw new NullPointerException("Null User Class Parameter/s");
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User(Long id, String username, String email, String password) throws NullPointerException {
		if (id == null || username == null || email == null || password == null)
			throw new NullPointerException("Null User Class Parameter/s");
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public String getUsername() {return username;}

	public void setUsername(String username) {this.username = username;}

	public String getEmail() {return email;}

	public void setEmail(String email) {this.email = email;}

	public String getPassword() {return password;}

	public void setPassword(String password) {this.password = password;}

	@Override
	public int hashCode() {
		return id.hashCode() + username.hashCode() + email.hashCode();
	}

	@Override
	public String toString() {return id + " " + username + " " + email;}
}

