package com.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="groups")
public class Group extends BaseModel {

    private String name;
    private String description;
    @ManyToOne
    private User createdBy;
    @ManyToMany
    private List<User> participants;
    @ManyToMany
    private List<User> admins;
    @OneToMany
    private List<Expense> expenses;
}
