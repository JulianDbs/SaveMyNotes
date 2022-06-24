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
	@DisplayName("UserInfo Repository | getUserInfoByEmail() Test")
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

	@Nested
	@DisplayName("UserInfo Repository | updateNoteCountByEmail() Method Test")
	@TestInstance(Lifecycle.PER_CLASS)
	public class UpdateNoteCountByEmailMethodTest {

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

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void updateNoteCountByEmailMethodDoesNotThrowTest() {
			//when
			try {
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
			//then
			assertDoesNotThrow(() -> {
				userInfoRepository.updateNoteCountByEmail("jack@jack.com", Long.valueOf(1));
			});
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly NullPointerExcception Test")
		public void updateNoteCountByEmailMethodThrowsExactlyNullPointerExceptionTest() {
			assertThrowsExactly(NullPointerException.class, () -> {
				userInfoRepository.updateNoteCountByEmail(null, null);
			});
		}

		@Test
		@Order(3)
		@DisplayName("Throws Exactly UserInfoNotFoundException Test")
		public void updateNoteCountByEmailMethodThrowsExactlyUserInfoNotFoundExceptionTest() {
			assertThrowsExactly(UserInfoNotFoundException.class, () -> {
				userInfoRepository.updateNoteCountByEmail("jack@jack.com", Long.valueOf(1));
			});
		}

		@Test
		@Order(4)
		@DisplayName("Not Null Test")
		public void updateNoteCountByEmailMethodNotNullTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//when
				userInfoRepository.updateNoteCountByEmail("jack@jack.com", Long.valueOf(1));
				UserInfo userInfo = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				//then
				assertNotNull(userInfo.getNoteCount());
			} catch (NullPointerException |  UserInfoAlreadyExistsException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(5)
		@DisplayName("Equals Test")
		public void updateNoteCountByEmailMethodEqualsTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//when
				userInfoRepository.updateNoteCountByEmail("jack@jack.com", Long.valueOf(1));
				UserInfo userInfo = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				//then
				assertEquals(Long.valueOf(1), userInfo.getNoteCount());
			} catch (NullPointerException | UserInfoAlreadyExistsException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Nested
	@DisplayName("UserInfo Repository | updateAccountExpiredByEmail() Method Test")
	@TestInstance(Lifecycle.PER_CLASS)
	public class UpdateAccountExpiredByEmailMethodTest {
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

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void updateAccountExpiredByEmailMethodDoesNotThrowTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//then
				assertDoesNotThrow( () -> {
					userInfoRepository.updateAccountExpiredByEmail("jack@jack.com", Boolean.valueOf(true));
				});
			} catch (NullPointerException | UserInfoAlreadyExistsException  e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly NullPointerException Test")
		public void updateAccountExpiredByEmailMethodThrowsExactlyNullPointerExceptionTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//then
				assertThrowsExactly(NullPointerException.class, () -> {
					userInfoRepository.updateAccountExpiredByEmail(null, null);
				});
			} catch (NullPointerException| UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(3)
		@DisplayName("Throws Exactly UserInfoNotFoundException Test")
		public void updateAccountExpiredByEmailMethodThrowsExactlyUserInfoNotFoundExceptionTest() {
			assertThrowsExactly(UserInfoNotFoundException.class, () -> {
				userInfoRepository.updateAccountExpiredByEmail("jack@jack.com", Boolean.valueOf(true));
			});
		}

		@Test
		@Order(4)
		@DisplayName("Not Null Test")
		public void updateAccountExpiredByEmailMethodNotNullTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//when
				userInfoRepository.updateAccountExpiredByEmail("jack@jack.com", Boolean.valueOf(true));
				//then
				UserInfo classUnderTest = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				assertNotNull(classUnderTest.isExpired());
			} catch (NullPointerException | UserInfoAlreadyExistsException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(5)
		@DisplayName("Equals Test")
		public void updateAccountExpiredByEmailMethodEqualsTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//when
				userInfoRepository.updateAccountExpiredByEmail("jack@jack.com", Boolean.valueOf(true));
				//then
				UserInfo classUnderTest = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				assertEquals(true, classUnderTest.isExpired());
			} catch (NullPointerException | UserInfoAlreadyExistsException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Nested
	@DisplayName("UserInfo Repository | updateAccountLockedByEmail() Method Test")
	@TestInstance(Lifecycle.PER_CLASS)
	public class UpdateAccountLockedByEmailMethodTest {

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

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void updateAccountLockedByEmailMethodDoesNotThrowTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//then
				assertDoesNotThrow(() -> {
					userInfoRepository.updateAccountLockedByEmail("jack@jack.com", Boolean.valueOf(true));
				});
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly NullPointerException Test")
		public void updateAccountLockedByEmailMethodThrowsExactlyNullPointerExceptionTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//then
				assertThrowsExactly(NullPointerException.class, () -> {
					userInfoRepository.updateAccountLockedByEmail(null, null);
				});
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(3)
		@DisplayName("Throws Exactly UserInfoNotFoundException Test")
		public void updateAccountLockedByEmailMethodThrowsExactlyUserInfoNotFoundExceptionTest() {
			assertThrowsExactly(UserInfoNotFoundException.class, () -> {
				userInfoRepository.updateAccountLockedByEmail("jack@jack.com", Boolean.valueOf(true));
			});
		}

		@Test
		@Order(4)
		@DisplayName("Not Null Test")
		public void updateAccountLockedByEmailMethodNotNullTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//whem
				userInfoRepository.updateAccountLockedByEmail("jack@jack.com", Boolean.valueOf(true));
				//then
				UserInfo classUnderTest = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				assertNotNull(classUnderTest.isLocked());
			} catch (NullPointerException | UserInfoAlreadyExistsException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(5)
		@DisplayName("Equals Test")
		public void updateAccountLockedByEmailMethodEqualsTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//whem
				userInfoRepository.updateAccountLockedByEmail("jack@jack.com", Boolean.valueOf(true));
				//then
				UserInfo classUnderTest = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				assertEquals(true, classUnderTest.isLocked());
			} catch (NullPointerException | UserInfoAlreadyExistsException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Nested
	@DisplayName("UserInfo Repository | updateCredentialsExpiredByEmail() Method Test")
	@TestInstance(Lifecycle.PER_CLASS)
	public class UpdateCredentialsExpiredByEmailMethodTest {

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

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void updateCredentialsExpiredByEmailMethodDoesNotThrowTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//then
				assertDoesNotThrow( () -> {
					userInfoRepository.updateCredentialsExpiredByEmail("jack@jack.com", Boolean.valueOf(true));
				});
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly NullPointerException Test")
		public void updateCredentialsExpiredByEmailMethodThrowsExactlyNullPointerExceptionTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//then
				assertThrowsExactly(NullPointerException.class, () -> {
					userInfoRepository.updateCredentialsExpiredByEmail(null, null);
				});
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(3)
		@DisplayName("Throws Exactly UserInfoNotFoundException Test")
		public void updateCredentialsExpiredByEmailMethodThrowsExactlyUserInfoNotFoundExceptionTest() {
			assertThrowsExactly(UserInfoNotFoundException.class, () -> {
				userInfoRepository.updateCredentialsExpiredByEmail("jack@jack.com", Boolean.valueOf(true));
			});
		}

		@Test
		@Order(4)
		@DisplayName("Not Null Test")
		public void updateCredentialsExpiredByEmailMethodNotNullTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//when
				userInfoRepository.updateCredentialsExpiredByEmail("jack@jack.com", Boolean.valueOf(true));
				//then
				UserInfo classUnderTest = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				assertNotNull(classUnderTest.isCredentialsExpired());
			} catch (NullPointerException | UserInfoAlreadyExistsException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(5)
		@DisplayName("Equals Test")
		public void updateCredentialsExpiredByEmailMethodEqualsTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//when
				userInfoRepository.updateCredentialsExpiredByEmail("jack@jack.com", Boolean.valueOf(true));
				//given
				UserInfo classUnderTest = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				assertEquals(true, classUnderTest.isCredentialsExpired());
			} catch (NullPointerException | UserInfoAlreadyExistsException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Nested
	@DisplayName("UserInfo Repository | updateAccountEnabledByEmail() Method Test")
	@TestInstance(Lifecycle.PER_CLASS)
	public class UpdateAccountEnabledByEmailMethodTest {

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

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void updateAccountEnabledByEmailMethodDoesNotThrowTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//then
				assertDoesNotThrow( () -> {
					userInfoRepository.updateAccountEnabledByEmail("jack@jack.com", Boolean.valueOf(false));
				});
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly NullPointerExcpetion Test")
		public void updateAccountEnabledByEmailMethodThrowsExactlyNullPointerExceptionTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//then
				assertThrowsExactly(NullPointerException.class, () -> {
					userInfoRepository.updateAccountEnabledByEmail(null, null);
				});
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(3)
		@DisplayName("Throws Exactly UserInfoNotFoundException Test")
		public void updateAccountEnabledByEmailMethodThrowsExactlyUserInfoNotFoundExceptionTest() {
			assertThrowsExactly(UserInfoNotFoundException.class, () -> {
				userInfoRepository.updateAccountEnabledByEmail("jack@jack.com", Boolean.valueOf(false));
			});
		}

		@Test
		@Order(4)
		@DisplayName("Not Null Test")
		public void updateAccountEnabledByEmailMethodNotNullTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//when
				userInfoRepository.updateAccountExpiredByEmail("jack@jack.com", Boolean.valueOf(false));
				UserInfo classUnderTest = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				assertNotNull(classUnderTest.isEnabled());
			} catch (NullPointerException | UserInfoAlreadyExistsException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(5)
		@DisplayName("Equals Test")
		public void updateAccountEnabledByEmailMethodEqualsTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//when
				userInfoRepository.updateAccountEnabledByEmail("jack@jack.com", Boolean.valueOf(false));
				//then
				UserInfo classUnderTest = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				assertEquals(false, classUnderTest.isEnabled());
			} catch (NullPointerException | UserInfoAlreadyExistsException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Nested
	@DisplayName("UserInfo Repository | removeUserInfoByEmail() Method Test")
	@TestInstance(Lifecycle.PER_CLASS)
	public class removeUserInfoByEmailMethodTest {

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

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void removeUserInfoByEmailMethodDoesNotThrowTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//then
				assertDoesNotThrow( () -> {
					userInfoRepository.removeUserInfoByEmail("jack@jack.com");
				});
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly NullPointerException Test")
		public void removeUserInfoByEmailMethodThrowsExactlyNullPointerExceptionTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//then
				assertThrowsExactly(NullPointerException.class, () -> {
					userInfoRepository.removeUserInfoByEmail(null);
				});
			} catch (NullPointerException | UserInfoAlreadyExistsException e) {
				e.printStackTrace();
			}
		}

		@Test
		@Order(3)
		@DisplayName("Throws Exactly UserInfoNotFoundException Test")
		public void removeUserInfoByEmailMethodThrowsExactlyUserInfoNotFoundExceptionTest() {
			assertThrowsExactly(UserInfoNotFoundException.class, () -> {
				userInfoRepository.removeUserInfoByEmail("jack@jack.com");
			});
		}

		@Test
		@Order(4)
		@DisplayName("UserInfo Removed Confirmation Test")
		public void removeUserInfoByEmailMethodTest() {
			try {
				//given
				userInfoRepository.addNewUserInfo(new UserInfo("jack@jack.com"));
				//when
				userInfoRepository.removeUserInfoByEmail("jack@jack.com");
				//them
				assertThrowsExactly(UserInfoNotFoundException.class, () -> {
					UserInfo userInfo = userInfoRepository.getUserInfoByEmail("jack@jack.com");
				});
			} catch (NullPointerException | UserInfoAlreadyExistsException | UserInfoNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
