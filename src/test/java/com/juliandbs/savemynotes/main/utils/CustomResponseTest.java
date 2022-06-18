package com.juliandbs.savemynotes.main.utils;

import com.juliandbs.savemynotes.main.utils.CustomResponse;

import org.springframework.ui.ConcurrentModel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.LinkedList;
import java.lang.NullPointerException;

@Tag("unitary")
@DisplayName("CustomResponse Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomResponseTest {

	public static List<String> usernameErrorList = buildUsernameErrorList();
	public static List<String> buildUsernameErrorList() {
		List<String> list = new LinkedList<>();
		list.add("Null or Empty Username");
		list.add("Character/s not valid");
		list.add("Username length must be greater than 3 and less than 21");
		return list;
	}

	public static ConcurrentModel model = buildModel();
	public static ConcurrentModel buildModel() {
		ConcurrentModel model = new ConcurrentModel();
		model.addAttribute("usernameErrors", usernameErrorList);
		return model;
	}

	public static final String url = "login/login";

	@Nested
	@DisplayName("Constructor With Parameters Test")
	public class ConstructorWithParameters {

		@Test
		@Order(1)
		@DisplayName("Does Not Throw Test")
		public void constructorWithParametersDoesNotThrowTest() {
			assertDoesNotThrow( () -> {
				CustomResponse customResponse = new CustomResponse(url, model);
			});
		}

		@Test
		@Order(2)
		@DisplayName("Throws Exactly NullPointerException Test")
		public void constructorWithParametersThrowsExactlyNullPointerException() {
			assertThrowsExactly(NullPointerException.class, () -> {
				CustomResponse customResponse = new CustomResponse(null, null);
			});
		}

		@Test
		@Order(3)
		@DisplayName("Not Null Test")
		public void constructorWithParametersNotNullTest() {
			CustomResponse customResponse = new CustomResponse(url, model);
			assertAll(
				() -> assertNotNull(customResponse.getUrl()),
				() -> assertNotNull(customResponse.getModel()),
				() -> assertNotNull(customResponse.hashCode()),
				() -> assertNotNull(customResponse.toString())
			);
		}

		@Test
		@Order(4)
		@DisplayName("Equals Test")
		public void constructorWithParametersEqualsTest() {
			CustomResponse customResponse = new CustomResponse(url, model);
			assertAll(
				() -> assertEquals(url, customResponse.getUrl()),
				() -> assertEquals(model.hashCode(), customResponse.getModel().hashCode())
			);
		}
	}
}
