package com.juliandbs.savemynotes.persistence.repositories;

import com.juliandbs.savemynotes.persistence.models.UserInfo;
import com.juliandbs.savemynotes.persistence.exceptions.UserInfoAlreadyExistsException;
import com.juliandbs.savemynotes.persistence.exceptions.UserInfoNotFoundException;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.time.LocalDate;
import java.lang.NullPointerException;

@NoRepositoryBean
public interface CustomizedUserInfoRepository {

	//create
	public void addNewUserInfo(UserInfo userInfo) throws NullPointerException, UserInfoAlreadyExistsException;

	//read
	@Query(nativeQuery = true, value = "SELECT * FROM user_info WHERE user_info.email=:email")
	public Optional<UserInfo> findUserInfoByEmail(@Param("email") String email);

	//update
	public void updateNoteCountByEmail(final String email, final Long note_count) throws NullPointerException, UserInfoNotFoundException;

	public void updateAccountExpiredByEmail(final String email, final Boolean account_expired) throws NullPointerException, UserInfoNotFoundException;

	public void updateAccountLockedByEmail(final String email, final Boolean account_locked) throws NullPointerException, UserInfoNotFoundException;

	public void updateCredentialsExpiredByEmail(final String email, final Boolean credentials_expired) throws NullPointerException, UserInfoNotFoundException;

	public void updateAccountEnabledByEmail(final String email, final Boolean account_enabled) throws NullPointerException, UserInfoNotFoundException;

	//delete
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM user_info WHERE user_info.email=:email")
	public void deleteUserInfoByEmail(@Param("email") String email);

}
