package com.juliandbs.savemynotes.security.userdetails;

import com.juliandbs.savemynotes.persistence.models.User;
import com.juliandbs.savemynotes.persistence.models.UserInfo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.lang.NullPointerException;

public class UserDetailsImpl implements UserDetails {

	private User user;

	private UserInfo userInfo;

	public UserDetailsImpl() {}

	public UserDetailsImpl(User user, UserInfo userInfo) throws NullPointerException {
		if (user == null || userInfo == null)
			throw new NullPointerException();
		this.user = user;
		this.userInfo = userInfo;
	}

	public boolean isValid() {
		return user != null && userInfo != null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("User"));
	}

	@Override
	public String getPassword() {return user.getPassword();}

	@Override
	public String getUsername() {return user.getEmail();}

	@Override
	public boolean isAccountNonExpired() {return !userInfo.isExpired();}

	@Override
	public boolean isAccountNonLocked() {return !userInfo.isLocked();}

	@Override
	public boolean isCredentialsNonExpired() {return !userInfo.isCredentialsExpired();}

	@Override
	public boolean isEnabled() {return userInfo.isEnabled();}

}
