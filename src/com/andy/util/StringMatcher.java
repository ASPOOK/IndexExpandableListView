package com.andy.util;

/**
 * 如果是中文，获取第一个字母匹配;pinyin4j
 * 
 * @author Andy
 * 
 */
public class StringMatcher {

	public static boolean match(String value, String keyword) {
		if (value == null || keyword == null)
			return false;
		if (keyword.length() > value.length())
			return false;

		int i = 0, j = 0;
		do {
			if (keyword.charAt(j) == value.charAt(i)) {
				i++;
				j++;
			} else if (j > 0)
				break;
			else
				i++;
		} while (i < value.length() && j < keyword.length());

		return (j == keyword.length()) ? true : false;
	}

}
