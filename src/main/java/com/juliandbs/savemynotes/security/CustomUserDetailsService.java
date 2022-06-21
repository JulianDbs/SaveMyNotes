package com.juliandbs.savemynotes.security;

import com.juliandbs.savemynotes.persistence.repositories.UserRepository;
import com.juliandbs.savemynotes.persistence.repositories.UserInfoRepository;
import com.juliandbs.savemynotes.security.userdetails.UserDetailsImpl;
import com.juliandbs.savemynotes.persistence.models.User;
import com.juliandbs.savemynotes.persistence.models.UserInfo;
import com.juliandbs.savemynotes.persistence.exceptions.UserNotFoundException;
import com.juliandbs.savemynotes.persistence.exceptions.UserInfoNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserDetailsImpl loadUserByUsername(final String username) throws UsernameNotFoundException {
		try {
			User user = userRepository.getUserByEmail(username);
			UserInfo userInfo = userInfoRepository.getUserInfoByEmail(username);
			UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user, userInfo);
			return userDetailsImpl;
		} catch (UserNotFoundException | UserInfoNotFoundException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(username);
		}
	}
}
