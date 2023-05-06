package com.example.services.settleup.strategies;

import com.example.models.ExpenseOwingUser;
import com.example.models.ExpensePayingUser;
import com.example.models.User;
import com.example.services.settleup.Transaction;

import java.util.*;

public class GreedySettleUpExpenseStrategy implements SettleUpTransactionsCalculatorStrategy{
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

        PriorityQueue<Record> negativeExpenses = new PriorityQueue<>();
        PriorityQueue<Record> positiveExpenses = new PriorityQueue<>((a, b)-> (int) (b.pendingAmount - a.pendingAmount));

        for(User user : extraAmounts.keySet()) {

            if(extraAmounts.get(user) < 0) {
                negativeExpenses.add(new GreedySettleUpExpenseStrategy.Record(user, extraAmounts.get(user)));
            } else {
                positiveExpenses.add(new GreedySettleUpExpenseStrategy.Record(user,extraAmounts.get(user)));
            }
        }

        List<Transaction> transactions = new ArrayList<>();

        while(!positiveExpenses.isEmpty() && !negativeExpenses.isEmpty()) {
            Record negative = negativeExpenses.remove();
            Record positive = positiveExpenses.remove();

            if (positive.pendingAmount > Math.abs(negative.pendingAmount)) {
                transactions.add(
                        new Transaction(negative.user, positive.user, Math.abs(negative.pendingAmount))
                );
                positiveExpenses.add(new Record(positive.user, positive.pendingAmount - Math.abs(negative.pendingAmount)));
            } else {
                transactions.add(
                        new Transaction(negative.user, positive.user, positive.pendingAmount)
                );
                negativeExpenses.add(new Record(negative.user, negative.pendingAmount + positive.pendingAmount));
            }
        }
        return transactions;
    }
}
