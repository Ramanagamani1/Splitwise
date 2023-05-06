package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseModel{
    private String name;
    private String phoneNumber;
    private String hashedpassword;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + phoneNumber + '\'' +
                ", hashedpassword='" + hashedpassword + '\'' +
                '}';
    }
}
