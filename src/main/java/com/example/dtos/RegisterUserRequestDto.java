package com.example.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequestDto {

    private String name;
    private String phoneNumber;
    private String hashedPassword;
}
