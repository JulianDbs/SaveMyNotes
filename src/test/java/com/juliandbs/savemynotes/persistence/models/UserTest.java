package com.juliandbs.savemynotes.persistence.models;

import com.juliandbs.savemynotes.persistence.models.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;

@Tag("unitary")
@DisplayName("User Model Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {

	@Nested
	@DisplayName("Empty Constructor Test")
	public class EmptyConstructorTest {

		public User user = new User();

		@Test
		@Order(1)
		@DisplayName("Throws Exactly NullPointerException")
		public void emptyConstrcutorThrowsExactlyTest() {
			assertThrowsExactly( NullPointerException.class, () -> {
				user.hashCode();
			});
		}

		@Test
		@Order(2)
		@DisplayName("Null Test")
		public void emptyConstructorNullTest() {
			assertAll(
				() -> assertNull(user.getId()),
				() -> assertNull(user.getUsername()),
				() -> assertNull(user.getEmail()),
				() -> assertNull(user.getPassword())
			);
		}

		@Test
		@Order(3)
		@DisplayName("Equals Test")
		public void emptyConstructorEqualsTest() {
			assertEquals("null null null", user.toString());
		}
	}

	@Nested
	@DisplayName("Constructor With 3 Parameters Test")
	public class ConstructorWith3ParametersTest {
 
		@Test
                @Order(1)
                @DisplayName("Does Not Throw Test")
                public void constructorWithParametersDoesNotThrowTest() {
                        assertDoesNotThrow( () -> {
                                User user = new User("jack", "jack@jack.com", "123456");

                        });
                }

                @Test
                @Order(2)
                @DisplayName("Throws Exactly NullPointerException Test")
                public void constructorWithParametersThrowsExacltyNullPointerException() {
                        assertThrowsExactly(NullPointerException.class, () -> {
                                User user = new User("jack", null, "123456");
                        });
                }

                @Test
                @Order(3)
                @DisplayName("Not Null Test")
                public void constructorWithParametersNotNullTest() {
                        User user = new User("jack", "jack@jack.com", "123456");
                        assertAll(
                                () -> assertNotNull(user.getUsername()),
                                () -> assertNotNull(user.getEmail()),
                                () -> assertNotNull(user.getPassword()),
                                () -> assertNotNull(user.toString())
                        );
                }

                @Test
                @Order(4)
                @DisplayName("Equals Test")
                public void constructorWithParametersEqualsTest() {
                        User user = new User("jack", "jack@jack.com", "123456");
                        assertAll(
                                () -> assertEquals(String.valueOf("jack"), user.getUsername()),
                                () -> assertEquals(String.valueOf("jack@jack.com"), user.getEmail()),
                                () -> assertEquals(String.valueOf("123456"), user.getPassword()),
                                () -> assertEquals(String.valueOf("null jack jack@jack.com"), user.toString())
                        );
                }
	}

	@Nested
	@DisplayName("Constructor With 4 Parameters Test")
	public class ConstructorWith4ParametersTest {

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void constructorWithParametersDoesNotThrowTest() {
			assertDoesNotThrow( () -> {
				User user = new User(Long.valueOf(1), "jack", "jack@jack.com", "123456");

			});
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly NullPointerException Test")
		public void constructorWithParametersThrowsExacltyNullPointerException() {
			assertThrowsExactly(NullPointerException.class, () -> {
				User user = new User(null, "jack", null, "123456");
			});
		}

		@Test
		@Order(3)
		@DisplayName("Not Null Test")
		public void constructorWithParametersNotNullTest() {
			User user = new User(Long.valueOf(1), "jack", "jack@jack.com", "123456");
			assertAll(
				() -> assertNotNull(user.getId()),
				() -> assertNotNull(user.getUsername()),
				() -> assertNotNull(user.getEmail()),
				() -> assertNotNull(user.getPassword()),
				() -> assertNotNull(user.hashCode()),
				() -> assertNotNull(user.toString())
			);
		}

		@Test
		@Order(4)
		@DisplayName("Equals Test")
		public void constructorWithParametersEqualsTest() {
			User user = new User(Long.valueOf(1), "jack", "jack@jack.com", "123456");
			assertAll(
				() -> assertEquals(Long.valueOf(1), user.getId()),
				() -> assertEquals(String.valueOf("jack"), user.getUsername()),
				() -> assertEquals(String.valueOf("jack@jack.com"), user.getEmail()),
				() -> assertEquals(String.valueOf("123456"), user.getPassword()),
				() -> assertEquals(711210515, user.hashCode()),
				() -> assertEquals(String.valueOf("1 jack jack@jack.com"), user.toString())
			);
		}
	}

}
