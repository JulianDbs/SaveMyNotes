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

	@Override
	public default void updateNoteCountByEmail(final String email, final Long note_count) throws NullPointerException, UserInfoNotFoundException {
                if (email == null || note_count == null)
                        throw new NullPointerException();
                UserInfo userInfo = this.getUserInfoByEmail(email);
                userInfo.setNoteCount(note_count);
                this.saveAndFlush(userInfo);
        }

	@Override
	public default void updateAccountExpiredByEmail(final String email, final Boolean account_expired) throws NullPointerException, UserInfoNotFoundException {
		if (email == null || account_expired == null)
			throw new NullPointerException();
		UserInfo userInfo = this.getUserInfoByEmail(email);
		userInfo.setAccountExpired(account_expired);
		this.saveAndFlush(userInfo);
	}

	@Override
	public default void updateAccountLockedByEmail(final String email, final Boolean account_locked) throws NullPointerException, UserInfoNotFoundException {
		if (email == null || account_locked == null)
			throw new NullPointerException();
		UserInfo userInfo = this.getUserInfoByEmail(email);
		userInfo.setAccountLocked(account_locked);
		this.saveAndFlush(userInfo);
	}

	@Override
	public default void updateCredentialsExpiredByEmail(final String email, final Boolean credentials_expired) throws NullPointerException, UserInfoNotFoundException {
		if (email == null || credentials_expired == null)
			throw new NullPointerException();
		UserInfo userInfo = this.getUserInfoByEmail(email);
		userInfo.setCredentialsExpired(credentials_expired);
		this.saveAndFlush(userInfo);
	}

	@Override
	public default void updateAccountEnabledByEmail(final String email, final Boolean account_enabled) throws NullPointerException, UserInfoNotFoundException {
		if (email == null || account_enabled == null)
			throw new NullPointerException();
		UserInfo userInfo = this.getUserInfoByEmail(email);
		userInfo.setAccountEnabled(account_enabled);
		this.saveAndFlush(userInfo);
	}

	public default void removeUserInfoByEmail(final String email) throws NullPointerException, UserInfoNotFoundException {
		if (email == null)
			throw new NullPointerException();
		UserInfo userInfo = this.getUserInfoByEmail(email);
		this.deleteUserInfoByEmail(email);
		this.flush();
	}
}
