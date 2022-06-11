package com.juliandbs.savemynotes.persistence.repositories;

import com.juliandbs.savemynotes.persistence.models.User;
import com.juliandbs.savemynotes.persistence.exceptions.UserAlreadyExistsException;
import com.juliandbs.savemynotes.persistence.exceptions.PasswordNotValidException;
import com.juliandbs.savemynotes.persistence.exceptions.UserNotFoundException;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@NoRepositoryBean
public interface CustomizedUserRepository {
	//Create
	void addNewUser(User user) throws UserAlreadyExistsException;

        //Read
	@Query(nativeQuery = true, value = "SELECT * FROM users WHERE users.email=:email")
        public Optional<User> findUserByEmail(@Param("email") String email);

	//Update
        void updateUsernameByEmail(String newUsername, String email) throws UserNotFoundException;

        void updatePasswordByEmail(String password, String newPassword, String email) throws UserNotFoundException, PasswordNotValidException;

        //Delete
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM users WHERE users.email=:email")
        void deleteUserByEmail(String email);
}

