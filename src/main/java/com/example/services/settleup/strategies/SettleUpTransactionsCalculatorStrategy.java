package com.example.services.settleup.strategies;

import com.example.models.ExpenseOwingUser;
import com.example.models.ExpensePayingUser;
import com.example.services.settleup.Transaction;

import java.util.List;

public interface SettleUpTransactionsCalculatorStrategy {
    List<Transaction> getTransactions(List<ExpensePayingUser> expensePayingUsers,
                                      List<ExpenseOwingUser> expenseOwingUsers);
}
