package com.juliandbs.savemynotes.main.utils;

import java.lang.StringBuilder;
import java.lang.NullPointerException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CodeGenerator {

	public static String generateUniqueCode(final String email) throws NullPointerException {
		if (email == null)
			throw new NullPointerException();
		StringBuilder builder = new StringBuilder();
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		String part_a = String.valueOf(email.hashCode()).replace("-", "");
		String part_b = String.valueOf(localDate.hashCode()).replace("-", "");
		String part_c = String.valueOf(localTime.hashCode()).replace("-", "");
		builder.append(part_a)
			.append("-")
			.append(part_b)
			.append("-")
			.append(part_c);
		return builder.toString();
	}
}
