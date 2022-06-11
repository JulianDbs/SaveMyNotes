package com.juliandbs.savemynotes.persistence.repository;

import com.juliandbs.savemynotes.SavemynotesApplication;
import com.juliandbs.savemynotes.persistence.repositories.CustomPostgresqlContainer;
import com.juliandbs.savemynotes.persistence.repositories.UserRepository;
import com.juliandbs.savemynotes.persistence.models.User;
import com.juliandbs.savemynotes.persistence.exceptions.UserNotFoundException;
import com.juliandbs.savemynotes.persistence.exceptions.UserAlreadyExistsException;
import com.juliandbs.savemynotes.persistence.exceptions.PasswordNotValidException;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SavemynotesApplication.class)
@ActiveProfiles("tc")
@DisplayName("User Repository | Live Integration Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTCIntegrationTest {

	@Autowired
	private UserRepository userRepository;

	@Container
	public static PostgreSQLContainer postgreSQLContainer = CustomPostgresqlContainer.getInstance();


	@Nested
	@DisplayName("addNewUser() Method Test")
	public class saveNewUserMethodTest {

		@BeforeEach
		public void init() {
			userRepository.deleteAll();
		}

		@Test
		@Order(1)
		@DisplayName("Does Not Trow Test")
		public void addNewUserMethodDoesNotThrowTest() {
			//given
			User user = new User("jack", "jack@jack.com", "123456");
			//then
			assertDoesNotThrow( () -> {
				userRepository.addNewUser(user);
			} );
		}

		@Test
		@Order(2)
		@DisplayName("Trows Exaclty UserAlreadyExists Exception Test")
		public void addNewUserMethodThrowsExactlyUserAlreadyExistsExceptionTest() {
				//given
				User user = new User("jack", "jack@jack.com", "123456");
				try {userRepository.addNewUser(user);} catch (UserAlreadyExistsException e){}
				//then
				assertThrowsExactly(UserAlreadyExistsException.class, () -> {
					userRepository.addNewUser(new User("jack", "jack@jack.com", "123456"));
				});

		}

		@Test
		@Order(3)
		@DisplayName("No Null Test")
		public void addNewUserMethodNotNullTest() {
			//given
			User user = new User("jack", "jack@jack.com", "123456");
			try {userRepository.addNewUser(user);} catch (UserAlreadyExistsException e){}
			//when
			User result = userRepository.findUserByEmail("jack@jack.com").get();
			//then
			assertNotNull(result);
		}

		@Test
		@Order(4)
		@DisplayName("Equals Test")
		public void addNewUserMethodEqualsTest() {
			//given
			User user = new User("jack", "jack@jack.com", "123456");
			try {userRepository.addNewUser(user);} catch (UserAlreadyExistsException e) {}
			//when
			User result = userRepository.findUserByEmail("jack@jack.com").get();
			//them
			assertEquals("jack", result.getUsername());
			assertEquals("jack@jack.com", result.getEmail());
			assertEquals("123456", result.getPassword());
		}
	}

	@Nested
	@DisplayName("findUserByEmail() Method Test")
	@TestInstance(Lifecycle.PER_CLASS)
	public class findUserByEmailMethodTest {

		private User user = new User("jack", "jack@jack.com", "123456");

		@BeforeAll
		public void setup() {
			userRepository.deleteAll();
			userRepository.save(user);
		}

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void findUserByEmailDoesNotThrowTest() {
			//when //then
			assertDoesNotThrow( () -> {
				Optional<User> result = Optional.ofNullable(userRepository.findUserByEmail("jack@jack.com"))
								.orElseThrow(UserNotFoundException::new);
			});

		}

		@Test
		@Order(2)
		@DisplayName("Not Null Test")
		public void findUserByEmailNotNullTest() {
			assertNotNull(userRepository.findUserByEmail("jack@jack.com").get());
		}

		@Test
		@Order(3)
		@DisplayName("Equals Test")
		public void findUserByEmailEqualsTest() {
			assertEquals(user.toString(), userRepository.findUserByEmail("jack@jack.com").get().toString());
		}

	}

	@Nested
	@DisplayName("getUserByEmail() Method Test")
	@TestInstance(Lifecycle.PER_CLASS)
	public class getUserByEmailMethodTest {
		private User user = new User("jacks", "jack@jack.com", "1234565");

		@BeforeAll
		public void setup() {
			userRepository.deleteAll();
		}

		@Test
		@Order(1)
		@DisplayName("Throws Exactly UserNotFoundException")
		public void findUserByEmailThrowsExactlyUserNotFoundExceptionTest() {
			assertThrowsExactly(UserNotFoundException.class, () -> {
				userRepository.getUserByEmail("jack@jack.com");
			});
		}
	}

	@Nested
	@DisplayName("updateUsernameByEmail() Method Test")
	public class updateUsernameByEmailTest {

		@BeforeEach()
		public void init() {
			userRepository.deleteAll();
		}

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void updateUsernameByEmailDoesNotThrowTest() {
			//given
			userRepository.saveAndFlush(new User("jack", "jack@jack.com", "123456"));
			//when//then
			assertDoesNotThrow( () -> {
				userRepository.updateUsernameByEmail("jack@jack.com", "james");
			});
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly UserNotFoundException Test")
		public void updateUsernameByEmailThrowsExactlyUserNotFound() {
			//given
			userRepository.saveAndFlush(new User("jack", "jack@jack.com", "123456"));
			//when//then
			assertThrowsExactly(UserNotFoundException.class, () -> {
				userRepository.updateUsernameByEmail("james@james.com", "james");
			});
		}

		@Test
		@Order(3)
		@DisplayName("Not Null Test")
		public void updateUsernameByEmailNotNull() throws UserNotFoundException {
			//given
			userRepository.saveAndFlush(new User("jack", "jack@jack.com", "123456"));
			//when
			userRepository.updateUsernameByEmail("jack@jack.com", "james");
			//then
			assertNotNull(userRepository.findUserByEmail("jack@jack.com").get());
		}

		@Test
		@Order(4)
		@DisplayName("Equals Test")
		public void updateUsernameByEmailEqualseTest() throws UserNotFoundException {
			//given
			userRepository.saveAndFlush(new User("jack", "jack@jack.com", "123456"));
			//when
			userRepository.updateUsernameByEmail("jack@jack.com", "james");
			User result = userRepository.findUserByEmail("jack@jack.com").get();
			//then
			assertEquals("james", result.getUsername());
		}
	}

	@Nested
	@DisplayName("updatePasswordByEmail() Method Test")
	public class updatePasswordByEmailTest {
		@BeforeEach
		public void init() {
			userRepository.deleteAll();
		}

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void updatePasswordByEmailDoesNotThrowTest() {
			//given
			userRepository.saveAndFlush(new User("jack", "jack@jack.com", "123456"));
			//when//then
			assertDoesNotThrow( () -> {
				userRepository.updatePasswordByEmail("jack@jack.com", "123456", "654321");
			});
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly PasswordNotValidException Test")
		public void updatePasswordByEmailTrhowsExactlyPasswordNotValidExceptionTest() {
			//given
			userRepository.saveAndFlush(new User("jack", "jack@jack.com", "123456"));
			//when//then
			assertThrowsExactly(PasswordNotValidException.class, () -> {
				userRepository.updatePasswordByEmail("jack@jack.com", "abcdef", "654321");
			});

		}

		@Test
		@Order(3)
		@DisplayName("Throws Exactly UserNotFoundException Test")
		public void updatePasswordByEmailThrowsExactlyUserNotFoundExceptionTest() {
			//given
			userRepository.saveAndFlush(new User("jack", "jack@jack.com", "123456"));
			//when//then
			assertThrowsExactly(UserNotFoundException.class, () -> {
				userRepository.updatePasswordByEmail("james@james.com", "123456", "654321");
			});
		}

		@Test
		@Order(4)
		@DisplayName("Not Null Test")
		public void updatePasswordByEmailNotNullTest() throws UserNotFoundException, PasswordNotValidException {
			//given
			userRepository.saveAndFlush(new User("jack", "jack@jack.com", "123456"));
			//when
			userRepository.updatePasswordByEmail("jack@jack.com","123456", "654321");
			//then
			assertNotNull(userRepository.findUserByEmail("jack@jack.com").get());
		}

		@Test
		@Order(5)
		@DisplayName("Equals Test")
		public void updatePasswordByEmailEqualsTest() throws UserNotFoundException, PasswordNotValidException {
			//given
			userRepository.save(new User("jack", "jack@jack.com", "123456"));
			//when
			userRepository.updatePasswordByEmail("jack@jack.com", "123456", "654321");
			User result = userRepository.findUserByEmail("jack@jack.com").get();
			//then
			assertEquals("654321", result.getPassword());
		}
	}

	@Nested
	@DisplayName("removeUserByEmail() Method Test")
	@TestInstance(Lifecycle.PER_CLASS)
	public class removeUserByEmailMethodTest {

		@BeforeEach
		public void init() {
			userRepository.deleteAll();
		}

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void removeUserByEmailMethodDoesNotThrowTest() {
			//given
			userRepository.saveAndFlush(new User("jack", "jack@jack.com", "123456"));
			//then
			assertDoesNotThrow( () -> {
				userRepository.removeUserByEmail("jack@jack.com");
			});
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly UserNotFoundException Test")
		public void removeUserByEmailMethodThrowsUserNotFoundExceptionTest() {
			assertThrowsExactly(UserNotFoundException.class, () -> {
				userRepository.removeUserByEmail("jack@jack.com");}
			);
		}

	}
}
