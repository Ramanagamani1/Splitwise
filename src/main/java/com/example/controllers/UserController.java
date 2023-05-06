package com.example.controllers;

import com.example.dtos.RegisterUserRequestDto;
import com.example.dtos.RegisterUserResponseDto;
import com.example.dtos.UpdateProfileRequestDto;
import com.example.dtos.UpdateProfileResponseDto;
import com.example.models.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto) {
        User user = new User();
        user.setName(registerUserRequestDto.getName());
        user.setPhoneNumber(registerUserRequestDto.getPhoneNumber());
        user.setHashedpassword(registerUserRequestDto.getHashedPassword());

        User savedUser = userService.registerUser(
                registerUserRequestDto.getName(),
                registerUserRequestDto.getPhoneNumber(),
                registerUserRequestDto.getHashedPassword()
        );
        RegisterUserResponseDto userResponseDto = new RegisterUserResponseDto();
        userResponseDto.setUser(savedUser);
        return userResponseDto;
    }

    public UpdateProfileResponseDto updateProfile(UpdateProfileRequestDto request) {
        User user = userService.updateProfile(request.getUserId(),request.getNewPassword());

        UpdateProfileResponseDto response = new UpdateProfileResponseDto();
        response.setUser(user);
        return  response;
    }
}
