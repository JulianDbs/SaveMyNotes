package com.juliandbs.savemynotes.persistence.repositories;

import com.juliandbs.savemynotes.persistence.repositories.CustomizedUserRepository;
import com.juliandbs.savemynotes.persistence.models.User;
import com.juliandbs.savemynotes.persistence.exceptions.UserAlreadyExistsException;
import com.juliandbs.savemynotes.persistence.exceptions.UserNotFoundException;
import com.juliandbs.savemynotes.persistence.exceptions.PasswordNotValidException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomizedUserRepository {

	//Create
	@Override
	public default void addNewUser(User user) throws UserAlreadyExistsException {
		try {
			User result = this.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new UserAlreadyExistsException();
		}
		this.flush();
	}

	//Read
	public default User getUserByEmail(String email) throws UserNotFoundException {
		Optional<User> result = this.findUserByEmail(email);
		if (result.isEmpty())
			throw new UserNotFoundException();
		return result.get();
	}

	//Update
	@Override
	public default void updateUsernameByEmail(String email, String newUsername) throws UserNotFoundException {
		User user = this.getUserByEmail(email);
		user.setUsername(newUsername);
		this.saveAndFlush(user);
	}

	@Override
	public default void updatePasswordByEmail(String email, String password, String newPassword) throws PasswordNotValidException, UserNotFoundException {
			User user = this.getUserByEmail(email);
			if (!user.getPassword().equals(password))
				throw new PasswordNotValidException();
			user.setPassword(newPassword);
			this.saveAndFlush(user);
	}

	//Delete
	public default void removeUserByEmail(String email) throws UserNotFoundException {
		Optional<User> result = this.findUserByEmail(email);
                if (result.isEmpty())
                         throw new UserNotFoundException();
		this.deleteUserByEmail(email);
		this.flush();
	}
}
