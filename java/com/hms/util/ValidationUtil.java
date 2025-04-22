package com.hms.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    // 1. Full name: at least 6 characters, only letters and spaces
    public static boolean isValidFullName(String value) {
        return value != null && value.matches("^[a-zA-Z ]{6,}$");
    }

    // 2. Email: must be in format xyz@gmail.com
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@gmail\\.com$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    // 3. Phone number: starts with 97 or 98 and exactly 10 digits
    public static boolean isValidPhoneNumber(String number) {
        return number != null && number.matches("^(97|98)\\d{8}$");
    }

    // 4. Password: min 8 chars, 1 capital letter, 1 number, 1 special symbol
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(passwordRegex);
    }
    
    public boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}
}
