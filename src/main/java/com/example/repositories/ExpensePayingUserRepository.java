package com.example.repositories;

import com.example.models.Expense;
import com.example.models.ExpensePayingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensePayingUserRepository extends JpaRepository<ExpensePayingUser, Long> {
     List<ExpensePayingUser> findAllByExpense(Expense expense);
}
