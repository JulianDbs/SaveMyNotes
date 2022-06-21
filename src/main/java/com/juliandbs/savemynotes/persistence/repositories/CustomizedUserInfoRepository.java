package com.juliandbs.savemynotes.persistence.repositories;

import com.juliandbs.savemynotes.persistence.models.UserInfo;
import com.juliandbs.savemynotes.persistence.exceptions.UserInfoAlreadyExistsException;

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

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "INSERT INTO user_info (email, note_count) VALUES (:email, :note_count)")
	public void createNewUserInfo(@Param("email") String email, @Param("note_count") Long note_count);

	//read
	@Query(nativeQuery = true, value = "SELECT * FROM user_info WHERE user_info.email=:email")
	public Optional<UserInfo> findUserInfoByEmail(@Param("email") String email);

	//update
	@Query(nativeQuery = true, value = "UPDATE user_info SET (user_info.creation_date=:creation_date) WHERE user_info.email=:email")
	public void updateCreationDateByEmail(@Param("email") String email, @Param("creation_date") LocalDate creation_date);

	@Query(nativeQuery = true, value = "UPDATE user_info SET (user_info.note_count=:note_count) WHERE user_info.email=:email")
	public void updateNoteCountByEmail(@Param("email") String email, @Param("note_count") Long note_count);

	@Query(nativeQuery = true, value = "UPDATE user_info SET (user_info.account_expired=:account_expired) WHERE user_info.email=:email")
	public void updateAccountExpired(@Param("email") String email, @Param("account_expired") boolean account_expired);

	@Query(nativeQuery = true, value = "UPDATE user_info SET (user_info.account_locked=:account_locked) WHERE user_info.email=:email")
	public void updateAccountLocked(@Param("email") String email, @Param("account_locked") boolean account_locked);

	@Query(nativeQuery = true, value = "UPDATE user_info SET (user_info.credentials_exired=:credentials_expired) WHERE user_info.email=:email")
	public void updateCredentialsExpired(@Param("email") String email, @Param("credentials_expired") boolean credentials_expired);

	@Query(nativeQuery = true, value = "UPDATE user_info SET (user_info.account_enabled=:account_enabled) WHERE user_info.email=:email")
	public void updateAccountEnabled(@Param("email") String email, @Param("account_enabled") boolean account_enabled);

	//delete
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM user_info WHERE user_info.email=:email")
	public void deleteUserInfoByEmail(@Param("email") String email);
}
