package com.example.services.settleup;

import com.example.models.Expense;
import com.example.models.ExpenseOwingUser;
import com.example.models.ExpensePayingUser;
import com.example.models.Group;
import com.example.repositories.ExpenseOwingUserRepository;
import com.example.repositories.ExpensePayingUserRepository;
import com.example.repositories.GroupRepository;
import com.example.services.settleup.strategies.SettleUpTransactionsCalculatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {
    @Autowired
    private SettleUpTransactionsCalculatorStrategy settleUpTransactionsCalculatorStrategy;
    private ExpensePayingUserRepository expensePayingUserRepository;
    private ExpenseOwingUserRepository expenseOwingUserRepository;
    private GroupRepository groupRepository;

    @Autowired
    public SettleUpService(ExpensePayingUserRepository expensePayingUserRepository,
                           ExpenseOwingUserRepository expenseOwingUserRepository,
                           SettleUpTransactionsCalculatorStrategy settleUpTransactionsCalculatorStrategy) {
        this.expensePayingUserRepository = expensePayingUserRepository;
        this.expenseOwingUserRepository = expenseOwingUserRepository;
        this.settleUpTransactionsCalculatorStrategy = settleUpTransactionsCalculatorStrategy;
    }

    public List<Transaction> settleUpUser(Long userId) {
        return null;
    }

    public List<Transaction> settleUpGroup(Long groupId) {

        Optional<Group> groupOptional = groupRepository.findById(groupId);

        if(!groupOptional.isPresent()) {
            System.out.println("No group with that id present");
        }

        Group group = groupOptional.get();

        List<ExpensePayingUser> expensePayingUsers = new ArrayList<>();
        List<ExpenseOwingUser> expenseOwingUsers = new ArrayList<>();

        for(Expense expense : group.getExpenses()) {
            expensePayingUsers.addAll(expensePayingUserRepository.findAllByExpense(expense));
            expenseOwingUsers.addAll(expenseOwingUserRepository.findAllByExpense(expense));
        }

        return settleUpTransactionsCalculatorStrategy.getTransactions(expensePayingUsers,expenseOwingUsers);
    }
}
