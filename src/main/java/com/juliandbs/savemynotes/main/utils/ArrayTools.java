package com.juliandbs.savemynotes.main.utils;


public class ArrayTools {

	public static String stringArrayToString(String[] content) {
		StringBuilder result = new StringBuilder();
                for (int i = 0; i < content.length; i++) {
                        if (i != 0 && i != (content.length - 1))
                                result.append("\n");
                        result.append(content[i]);
                }
                return result.toString();
	}

	public static String[] stringToStringArray(String content) {
		return content.split("\n");
	}
}
