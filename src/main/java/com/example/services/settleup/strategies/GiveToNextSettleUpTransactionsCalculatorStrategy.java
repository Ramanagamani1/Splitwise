package com.example.services.settleup.strategies;

import com.example.models.ExpenseOwingUser;
import com.example.models.ExpensePayingUser;
import com.example.models.User;
import com.example.services.settleup.Transaction;

import java.util.*;

public class GiveToNextSettleUpTransactionsCalculatorStrategy implements SettleUpTransactionsCalculatorStrategy{

    public class Record {
        User user;
        double pendingAmount;

        Record(User user, Double pendingAmount) {
            this.user = user;
            this.pendingAmount = pendingAmount;
        }
    }

    @Override
    public List<Transaction> getTransactions(List<ExpensePayingUser> expensePayingUsers, List<ExpenseOwingUser> expenseOwingUsers) {
        Map<User, Double> extraAmounts = new HashMap<>();

        for(ExpensePayingUser expense: expensePayingUsers) {

            if(!extraAmounts.containsKey(expense.getUser())) {
                extraAmounts.put(expense.getUser(), 0.0);
            } else {
                extraAmounts.put(expense.getUser(),
                        extraAmounts.get(expense.getUser())+expense.getAmount());
            }
        }

        for(ExpenseOwingUser expense: expenseOwingUsers) {

            if(!extraAmounts.containsKey(expense.getUser())) {
                extraAmounts.put(expense.getUser(), 0.0);
            } else {
                extraAmounts.put(expense.getUser(),
                        extraAmounts.get(expense.getUser())-expense.getAmount());
            }
        }

        List<Transaction> transactions = new ArrayList<>();

        List<Map.Entry<User,Double>> entries = new ArrayList<>(extraAmounts.entrySet());

        int n = entries.size();

        for(int i=0;i<n-1;i++) {
            Transaction transaction = new Transaction();

            if(entries.get(i).getValue() < 0) {
                transaction.setFrom(entries.get(i).getKey());
                transaction.setTo(entries.get(i+1).getKey());
                transaction.setAmount(Math.abs(entries.get(i).getValue()));

                entries.get(i+1).setValue(entries.get(i+1).getValue() -Math.abs(entries.get(i).getValue()));
            } else {

                transaction.setFrom(entries.get(i+1).getKey());
                transaction.setTo(entries.get(i).getKey());
                transaction.setAmount(Math.abs(entries.get(i).getValue()));

                entries.get(i+1).setValue(entries.get(i+1).getValue() + Math.abs(entries.get(i).getValue()));
            }
        }
        return transactions;
    }
}
