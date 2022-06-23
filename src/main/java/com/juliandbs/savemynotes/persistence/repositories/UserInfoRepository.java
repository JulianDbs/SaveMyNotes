package com.juliandbs.savemynotes.persistence.repositories;

import com.juliandbs.savemynotes.persistence.models.UserInfo;
import com.juliandbs.savemynotes.persistence.repositories.CustomizedUserInfoRepository;
import com.juliandbs.savemynotes.persistence.exceptions.UserInfoNotFoundException;
import com.juliandbs.savemynotes.persistence.exceptions.UserInfoAlreadyExistsException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long>, CustomizedUserInfoRepository {

	@Override
	public default void addNewUserInfo(UserInfo userInfo) throws NullPointerException, UserInfoAlreadyExistsException {
		if (userInfo == null)
			throw new NullPointerException();
		try {
			UserInfo user = this.save(userInfo);
		} catch (DataIntegrityViolationException e) {
			throw new UserInfoAlreadyExistsException();
		}
		this.flush();
	}

	public default UserInfo getUserInfoByEmail(String email) throws NullPointerException, UserInfoNotFoundException {
		if (email == null)
			throw new NullPointerException();
		Optional<UserInfo> result = this.findUserInfoByEmail(email);
		if (!result.isPresent())
			throw new UserInfoNotFoundException();
		return result.get();
	}

}
