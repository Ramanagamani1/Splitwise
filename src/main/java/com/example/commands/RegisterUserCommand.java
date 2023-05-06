package com.example.commands;

import com.example.controllers.UserController;
import com.example.dtos.RegisterUserRequestDto;
import com.example.dtos.RegisterUserResponseDto;
import com.example.dtos.SettleUpUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RegisterUserCommand implements Command{

    private UserController userController;

    @Autowired
    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean canExecute(String input) {
        List<String> params = Arrays.stream( input.split(" ")).toList();
        if(!params.get(0).startsWith(CommandKeywords.RegisterUserCommand))
               return  false;

        if(params.size()!=4) {
            return false;
        }

        return true;
    }

    @Override
    public void execute(String input) {
        List<String> params = Arrays.stream( input.split(" ")).toList();
        String username = params.get(1);
        String phoneNumber = params.get(2);
        String password = params.get(3);

        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setName(username);
        requestDto.setPhoneNumber(phoneNumber);
        requestDto.setHashedPassword(password);

        RegisterUserResponseDto responseDto = userController.registerUser(requestDto);
        System.out.println(responseDto  .getUser());
        System.out.println("User Registered");
    }
}
