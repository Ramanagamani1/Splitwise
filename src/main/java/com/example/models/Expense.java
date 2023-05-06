package com.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="expenses")
public class Expense extends BaseModel {
    private double amount;
    private String description;
    private Date createdAt;
    @ManyToOne
    private User createdBy;
    @ManyToOne
    private Currency currency;
    @ManyToMany
    private List<User> participants;
}
