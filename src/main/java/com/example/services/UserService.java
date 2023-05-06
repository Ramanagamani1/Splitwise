package com.example.services;

import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.services.passwordEncoder.BCryptEncoder;
import com.example.services.passwordEncoder.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public  UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String name, String phoneNumber, String password) {
       User user = new User();
       user.setName(name);
       user.setPhoneNumber(phoneNumber);
       user.setHashedpassword(passwordEncoder.getEncodedPassword(password));

       User savedUser = userRepository.save(user);
       return savedUser;
    }

    public User updateProfile(Long userId, String newPassword) {
        User user = userRepository.findById(userId).get();
        user.setHashedpassword(passwordEncoder.getEncodedPassword(newPassword));
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public void login(Long userId, String password) {
        User user = userRepository.findById(userId).get();
        if(!passwordEncoder.matches(password, user.getHashedpassword())) {
            System.out.println("Invalid credentials");
        }
        else {
            System.out.println("Login Successful");
        }
    }
}
