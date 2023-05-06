package com.example.services.passwordEncoder;

public interface PasswordEncoder {

    String getEncodedPassword(String realPassword);

    boolean matches(String realPassword, String hashedPassword);
}
