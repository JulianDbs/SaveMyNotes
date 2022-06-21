package com.juliandbs.savemynotes.persistence.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

import java.lang.NullPointerException;
import java.time.LocalDate;

@Entity(name = "user_info")
@Table(name = "user_info")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", nullable = false, length = 40, unique = true)
	private String email;

	@Column(name = "creation_date", nullable = false)
	private LocalDate creation_date;

	@Column(name = "note_count", nullable = false)
	private Long note_count;

	@Column(name = "account_expired", nullable = false)
	private Boolean account_expired;

	@Column(name = "account_locked", nullable = false)
	private Boolean account_locked;

	@Column(name = "creadentials_expired", nullable = false)
	private Boolean credentials_expired;

	@Column(name = "account_enabled", nullable = false)
	private Boolean account_enabled;

	public UserInfo() {}

	public UserInfo(String email, Long note_count) throws NullPointerException {
		if (email == null || note_count == null)
			throw new NullPointerException();
		this.email = email;
		this.note_count = note_count;
	}

	public UserInfo(
		Long id,
		String email,
		LocalDate creation_date,
		Long note_count,
		Boolean account_expired,
		Boolean account_locked,
		Boolean credentials_expired,
		Boolean account_enabled
	) throws NullPointerException {
		if (id == null || email == null || creation_date == null || note_count == null ||
			account_expired == null || account_locked == null || credentials_expired == null || account_enabled == null)
			throw new NullPointerException();
		this.id = id;
		this.email = email;
		this.creation_date = creation_date;
		this.note_count = note_count;
		this.account_expired = account_expired;
		this.account_locked = account_locked;
		this.credentials_expired = credentials_expired;
		this.account_enabled = account_enabled;
	}

	public boolean isValid() {
		return id != null &&
			email != null &&
			creation_date != null &&
			note_count != null &&
			account_expired != null &&
			account_locked != null &&
			credentials_expired != null &&
			account_enabled != null;
	}

	public Long getId() {return id;}

	public String getEmail() {return email;}

	public LocalDate getCreationDate() {return creation_date;}

	public Long getNoteCount() {return note_count;}

	public boolean isExpired() {return account_expired.booleanValue();}

	public boolean isLocked() {return account_locked.booleanValue();}

	public boolean isCredentialsExpired() {return credentials_expired;}

	public boolean isEnabled() {return account_enabled.booleanValue();}

}
