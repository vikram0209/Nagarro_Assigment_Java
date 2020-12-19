package com.account.utility;

public class Utility {
	
	
	public static String maskNumber(String number, String mask) {

		int index = 0;
		StringBuilder masked = new StringBuilder();
		for (int i = 0; i < mask.length(); i++) {
			char c = mask.charAt(i);
			if (c == '#') {
				masked.append(number.charAt(index));
				index++;
			} else if (c == 'x') {
				masked.append(c);
				index++;
			} else {
				masked.append(c);
			}
		}
		return masked.toString();
	}
	
	
	
	

}
