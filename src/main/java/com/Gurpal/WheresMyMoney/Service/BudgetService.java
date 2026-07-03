package com.Gurpal.WheresMyMoney.Service;

import com.Gurpal.WheresMyMoney.Dto.BudgetRequestDto;
import com.Gurpal.WheresMyMoney.Dto.BudgetResponseDto;
import com.Gurpal.WheresMyMoney.Entity.Budget;
import com.Gurpal.WheresMyMoney.Entity.User;
import com.Gurpal.WheresMyMoney.Repository.BudgetRepository;
import com.Gurpal.WheresMyMoney.Repository.IncomeRepository;
import com.Gurpal.WheresMyMoney.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    UserRepository userRepository;
    BudgetRepository budgetRepository;
    IncomeRepository incomeRepository;

    @Autowired
    public BudgetService(UserRepository userRepository, BudgetRepository budgetRepository,IncomeRepository incomeRepository)
    {
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
        this.incomeRepository = incomeRepository;
    }

    public String addBudget(String userName, BudgetRequestDto budgetRequestDto)
    {
        if(budgetRepository.getTotalBudget(userName)+budgetRequestDto.getBudgetAmount()>incomeRepository.getTotalIncome(userName))
        {
            return "Budget Cannot Exceeds Income";
        }
        Budget budget = new Budget();
        budget.setBudgetAmount(budgetRequestDto.getBudgetAmount());
        String desiredCategory = budgetRequestDto.getBudgetCategory().trim().toUpperCase();
        budget.setBudgetCategory(desiredCategory);
        User user = userRepository.findByUserName(userName).get();
        budget.setUser(user);
        budgetRepository.save(budget);
        user.getBudgets().add(budget);

        return "Budget Details Added Successfully";
    }

    public List<BudgetResponseDto> getBudget(String userName)
    {
        List<BudgetResponseDto> dto = new ArrayList<>();
        List<Budget> budgets = userRepository.findByUserName(userName).get().getBudgets();

        for(Budget budget:budgets)
        {
            BudgetResponseDto budgetResponseDto = new BudgetResponseDto();
            budgetResponseDto.setBudgetId(budget.getBudgetId());
            budgetResponseDto.setBudgetAmount(budget.getBudgetAmount());
            budgetResponseDto.setBudgetCategory(budget.getBudgetCategory());
            budgetResponseDto.setCreatedAt(budget.getCreatedAt());
            dto.add(budgetResponseDto);
        }
        return dto;
    }

    public String deleteBudget(String userName,Long budgetId)
    {
        Optional<Budget> budget = budgetRepository.findById(budgetId);
        User user = userRepository.findByUserName(userName).get();
        if(budget.isPresent() && user.getBudgets().contains(budget.get()))
        {
            user.getBudgets().remove(budget.get());
            budgetRepository.delete(budget.get());
            return "Budget Detail Deleted Successfully";
        }
        else
        {
            return "Wrong Budget Id";
        }
    }
}
