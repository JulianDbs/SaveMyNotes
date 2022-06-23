package com.juliandbs.savemynotes.persistence.repositories;

import com.juliandbs.savemynotes.SavemynotesApplication;
import com.juliandbs.savemynotes.persistence.repositories.CustomPostgresqlContainer;
import com.juliandbs.savemynotes.persistence.repositories.UserRepository;
import com.juliandbs.savemynotes.persistence.models.User;
import com.juliandbs.savemynotes.persistence.repositories.UserInfoRepository;
import com.juliandbs.savemynotes.persistence.models.UserInfo;
import com.juliandbs.savemynotes.persistence.exceptions.UserInfoNotFoundException;
import com.juliandbs.savemynotes.persistence.exceptions.UserInfoAlreadyExistsException;

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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SavemynotesApplication.class)
@ActiveProfiles("tc")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserInfoRepositoryTCIntegrationTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Container
	public static PostgreSQLContainer postgreSQLContainer = CustomPostgresqlContainer.getInstance();

	@Nested
	@DisplayName("UserInfo Repository | addNewUserInfo(UserInfo userInfo) Method Test")
	@TestInstance(Lifecycle.PER_CLASS)
	public class AddNewUserInfoMethodWithUserInfoModelTest {

		@BeforeAll
		public void setup() {
			userRepository.deleteAll();
			userRepository.save(new User("jack", "jack@jack.com", "123456"));
		}

		@BeforeEach
		public void init() {
			userInfoRepository.deleteAll();
		}

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void addNewUserInfoMethodWithUserInfoModelDoesNotThrowTest() {
			//given
			UserInfo userInfo = new UserInfo(
				Long.valueOf(1), "jack@jack.com", LocalDate.now(), Long.valueOf(0),
				false, false, false, true
			);
			//when
			assertDoesNotThrow( () -> {
				userInfoRepository.addNewUserInfo(userInfo);
			});


		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly NullPointerException Test")
		public void addNewUserInfoMethodWithUserInfoModelThrowsExactlyNullPointerExceptionTest() {
			//when
			assertThrowsExactly(NullPointerException.class, () -> {
				userInfoRepository.addNewUserInfo(null);
			});
		}

		@Test
		@Order(3)
		@DisplayName("Throws Exactly UserInfoAlreadyExistsException Test")
		public void addNewUserInfoMethodWithUserInfoModelThrowsExactlyUserInfoAlreadyExistsExceptionTest() {
			//given
			UserInfo userInfo = new UserInfo(
				Long.valueOf(1), "jack@jack.com", LocalDate.now(), Long.valueOf(0),
				false, false, false, true
			);
			//when
			try {
				userInfoRepository.addNewUserInfo(userInfo);
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
			//then
			assertThrowsExactly(UserInfoAlreadyExistsException.class, () -> {
				userInfoRepository.addNewUserInfo(userInfo);
			});
		}

		@Test
		@Order(4)
		@DisplayName("Not Null Test")
		public void addNewUserInfoMethodWithUserInfoModelNotNullTest() {
			//given
			UserInfo userInfo = new UserInfo(
				Long.valueOf(1), "jack@jack.com", LocalDate.now(), Long.valueOf(0),
				false, false, false, true
			);
			//when
			try {
				userInfoRepository.addNewUserInfo(userInfo);
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
			//then
			try {
				UserInfo result = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				assertNotNull(result);
			} catch (NullPointerException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(5)
		@DisplayName("Equals Test")
		public void addNewUserInfoMethodWithUserInfoModelEqualsTest() {
			//given
			LocalDate currentDate = LocalDate.now();
			UserInfo userInfo = new UserInfo(
				Long.valueOf(1), "jack@jack.com", currentDate, Long.valueOf(0),
				false, false, false, true
			);
			//when
			try {
				userInfoRepository.addNewUserInfo(userInfo);
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
			//then
			try {
				UserInfo classUnderTest = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				assertAll(
					() -> assertEquals("jack@jack.com", classUnderTest.getEmail()),
					() -> assertEquals(currentDate, classUnderTest.getCreationDate()),
					() -> assertEquals(Long.valueOf(0), classUnderTest.getNoteCount()),
					() -> assertEquals(false, classUnderTest.isExpired()),
					() -> assertEquals(false, classUnderTest.isLocked()),
					() -> assertEquals(false, classUnderTest.isCredentialsExpired()),
					() -> assertEquals(true, classUnderTest.isEnabled())
				);
			} catch (NullPointerException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Nested
	@DisplayName("UserInfo Repository | addNewUserInfo(UserInfo userInfo) (using UserInfo one parameter constructor) Method Test")
	@TestInstance(Lifecycle.PER_CLASS)
	public class AddNewUserInfoMethodWithUserInfoOfOneParameterConstructorTest {

		@BeforeAll
		public void setup(){
			userRepository.deleteAll();
			userInfoRepository.deleteAll();
			User user = new User("jack", "jack@jack.com", "123456");
			userRepository.save(user);
		}

		@BeforeEach
		public void init() {
			userInfoRepository.deleteAll();
		}

		//Does Not Throw
		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void addNewUserInfoMethodWithUserInfoOfOneParameterConstructorDoesNotThrowTest() {
			userInfoRepository.deleteAll();
			//given
			UserInfo userInfo = new UserInfo("jack@jack.com");
			//then
			assertDoesNotThrow( () -> {
				userInfoRepository.addNewUserInfo(userInfo);
			});
		}

		//Throws Exactly
		@Test
		@Order(2)
		@DisplayName("Throws Exactly NullPointerException Test")
		public void addNewUserInfoMethodWithUserInfoOfOneParameterConstructorThrowsExactlyNullPointerException() {
			assertThrowsExactly(NullPointerException.class, () -> {
				userInfoRepository.addNewUserInfo(null);
			});
		}

		@Test
		@Order(3)
		@DisplayName("Throws Exactly UserInfoAlreadyExistsException Test")
		public void addNewUserInfoMethodWithUserInfoOfOneParameterConstructorThrowsExactlyUserInfoAlreadyExistsTest() {
			//given
			UserInfo userInfo = new UserInfo("jack@jack.com");
			//when
			try {
				userInfoRepository.addNewUserInfo(userInfo);
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
			//then
			assertThrowsExactly(UserInfoAlreadyExistsException.class, () -> {
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
			});
		}

		//Not Null
		@Test
		@Order(4)
		@DisplayName("Not Null Test")
		public void addNewUserInfoMethodWithUserInfoOfOneParameterConstructorNotNullTest() {
			//given
			try {
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
			//when
			try {
				UserInfo classUnderTest = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				//then
				assertNotNull(classUnderTest);
			} catch(NullPointerException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}

		//Equals
		@Test
		@Order(5)
		@DisplayName("Equals Test")
		public void addNewUserInfoMethodWithUserInfoOfOneParameterConstructorEqualsTest() {
			//given
			try {
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
			//when
			try {
				UserInfo userInfo = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				//then
				assertAll(
					() -> assertEquals("jack@jack.com", userInfo.getEmail()),
					() -> assertEquals(LocalDate.now(), userInfo.getCreationDate()),
					() -> assertEquals(Long.valueOf(0), userInfo.getNoteCount()),
					() -> assertEquals(false, userInfo.isExpired()),
					() -> assertEquals(false, userInfo.isLocked()),
					() -> assertEquals(false, userInfo.isCredentialsExpired()),
					() -> assertEquals(true, userInfo.isEnabled())
				);
			} catch (NullPointerException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Nested
	@DisplayName("UserInfoRepository | getUserInfoByEmail() Test")
	@TestInstance(Lifecycle.PER_CLASS)
	public class GetUserInfoByEmailTest {

		@BeforeAll
		public void setup() {
			userRepository.deleteAll();
			userInfoRepository.deleteAll();
			userRepository.save(new User("jack", "jack@jack.com", "123456"));
		}

		@BeforeEach
		public void init() {
			userInfoRepository.deleteAll();
		}

		//Does Not Throw Test
		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void getUserInfoByEmailDoesNotThrowTest() {
			//given
			try {
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
			//then
			assertDoesNotThrow( () -> {
				UserInfo userInfo = userInfoRepository.getUserInfoByEmail("jack@jack.com");
			});
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly NullPointerException Test")
		public void getUserInfoByEmailThrowsExactlyNullPointerExceptionTest() {
			//given
			try {
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
			//then
			assertThrowsExactly(NullPointerException.class, () -> {
				UserInfo result = userInfoRepository.getUserInfoByEmail(null);
			});
		}

		@Test
		@Order(3)
		@DisplayName("Throws Exactly UserInfoNotFoundException Test")
		public void getUserInfoByEmailThrowsExactlyUserInfoNotFoundExceptionTest() {
			//when
			assertThrowsExactly(UserInfoNotFoundException.class, () -> {
				UserInfo result = userInfoRepository.getUserInfoByEmail("jack@jack.com");
			});
		}

		@Test
		@Order(4)
		@DisplayName("Not Null Test")
		public void getUserInfoByEmailNotNullTest() {
			//given
			try {
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
			//when
			try {
				UserInfo classUnderTest = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				//then
				assertNotNull(classUnderTest);
			} catch (NullPointerException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(5)
		@DisplayName("Equals Test")
		public void getUserInfoByEmailEqualsTest() {
			//given
			try {
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
			//when
			try {
				UserInfo classUnderTest = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				//then
				assertAll(
					() -> assertEquals("jack@jack.com", classUnderTest.getEmail()),
					() -> assertEquals(LocalDate.now(), classUnderTest.getCreationDate()),
					() -> assertEquals(Long.valueOf(0), classUnderTest.getNoteCount()),
					() -> assertEquals(false, classUnderTest.isExpired()),
					() -> assertEquals(false, classUnderTest.isLocked()),
					() -> assertEquals(false, classUnderTest.isCredentialsExpired()),
					() -> assertEquals(true, classUnderTest.isEnabled())
				);
			} catch (NullPointerException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
