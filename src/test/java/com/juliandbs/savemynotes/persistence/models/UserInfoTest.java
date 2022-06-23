package com.juliandbs.savemynotes.persistence.models;

import com.juliandbs.savemynotes.persistence.models.UserInfo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.lang.NullPointerException;

@Tag("unitary")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserInfoTest {

	@Nested
	@DisplayName("UserInfo Model Test | Empty Constructor")
	public class EmptyConstructor{

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void emptyConstrcutorDoesNotThrowTest() {
			assertDoesNotThrow( () -> {
				UserInfo userInfo = new UserInfo();
			});
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly NullPointerException Test")
		public void emptyConstructorThrowsExcatlyNullPointerExceptionTest() {
			UserInfo userInfo = new UserInfo();
			assertAll(
				() -> assertThrowsExactly(NullPointerException.class , () -> {
					userInfo.isExpired();
				}),
				() -> assertThrowsExactly(NullPointerException.class, () -> {
					userInfo.isLocked();
				}),
				() -> assertThrowsExactly(NullPointerException.class, () -> {
					userInfo.isCredentialsExpired();
				}),
				() -> assertThrowsExactly(NullPointerException.class, () -> {
					userInfo.isExpired();
				})
			);
		}

		@Test
		@Order(3)
		@DisplayName("Null Test")
		public void emptyConstructorNullTest() {
			UserInfo userInfo = new UserInfo();
			assertAll(
				() -> assertNull(userInfo.getId()),
				() -> assertNull(userInfo.getEmail()),
				() -> assertNull(userInfo.getCreationDate()),
				() -> assertNull(userInfo.getNoteCount()),
				() -> assertFalse(userInfo.isValid())
			);
		}
	}

	@Nested
	@DisplayName("UserInfo Model Test | Constructor With Parameters")
	public class ConstructorWithParameters{

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void constructorWithParametersDoesNotThrowTest() {
			assertDoesNotThrow( () -> {
				UserInfo userInfo = new UserInfo(
					Long.valueOf(1),
					"jack@jack.com",
					LocalDate.now(),
					Long.valueOf(0),
					false, false, false,true
				);
			});
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly NullPointerException")
		public void constructorWithParametersThrowsExactlyNullPointerException() {
			assertThrowsExactly(NullPointerException.class, () -> {
				UserInfo userInfo = new UserInfo(
					Long.valueOf(1),
					"jack@jack.com",
					null,
					Long.valueOf(0),
					false, null, false,true
				);
			});
		}

		@Test
		@Order(3)
		@DisplayName("Not Null Test")
		public void constructorWithParametersNotNullTest() {
			UserInfo userInfo = new UserInfo(
				Long.valueOf(1),
				"jack@jack.com",
				LocalDate.now(),
				Long.valueOf(0),
				false, false, false,true
			);
			assertAll(
				() -> assertNotNull(userInfo.getId()),
				() -> assertNotNull(userInfo.getEmail()),
				() -> assertNotNull(userInfo.getCreationDate()),
				() -> assertNotNull(userInfo.getNoteCount()),
				() -> assertNotNull(userInfo.isExpired()),
				() -> assertNotNull(userInfo.isLocked()),
				() -> assertNotNull(userInfo.isCredentialsExpired()),
				() -> assertNotNull(userInfo.isEnabled()),
				() -> assertTrue(userInfo.isValid())
			);
		}

		@Test
		@Order(4)
		@DisplayName("Equals Test")
		public void constructorWithParametersEqualsTest() {
			LocalDate currentDate = LocalDate.now();
			UserInfo userInfo = new UserInfo(
				Long.valueOf(1),
				"jack@jack.com",
				currentDate,
				Long.valueOf(0),
				false, false, false,true
			);
			assertAll(
				() -> assertEquals(Long.valueOf(1), userInfo.getId()),
				() -> assertEquals("jack@jack.com", userInfo.getEmail()),
				() -> assertEquals(currentDate, userInfo.getCreationDate()),
				() -> assertEquals(Long.valueOf(0), userInfo.getNoteCount()),
				() -> assertEquals(false, userInfo.isExpired()),
				() -> assertEquals(false, userInfo.isLocked()),
				() -> assertEquals(false, userInfo.isCredentialsExpired()),
				() -> assertEquals(true, userInfo.isEnabled())
			);
		}
	}
}
