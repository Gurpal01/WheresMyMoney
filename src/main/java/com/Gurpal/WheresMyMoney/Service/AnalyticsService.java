package com.Gurpal.WheresMyMoney.Service;


import com.Gurpal.WheresMyMoney.Dto.AnalyticResponseDto;
import com.Gurpal.WheresMyMoney.Repository.BudgetRepository;
import com.Gurpal.WheresMyMoney.Repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsService {

    BudgetRepository budgetRepository;
    ExpenseRepository expenseRepository;

    @Autowired
    public AnalyticsService(BudgetRepository budgetRepository, ExpenseRepository expenseRepository)
    {
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<AnalyticResponseDto> getAnalysis(String userName)
    {
        List<AnalyticResponseDto> dto = new ArrayList<>();
        List<Object[]> list = expenseRepository.getCategoryWiseExpense(userName);

        for(Object[] obj:list)
        {
            AnalyticResponseDto analyticResponseDto = new AnalyticResponseDto();
            String category = (String) obj[0];
            Double expenseAmount = (Double) obj[1];
            Double budgetAmount = budgetRepository.getBudgetForCategory(userName,category);
            analyticResponseDto.setCategory(category);
            analyticResponseDto.setBudgetAmount(budgetAmount);
            analyticResponseDto.setExpenseAmount(expenseAmount);
            if(budgetAmount==0)
            {
                analyticResponseDto.setBudgetPercentageUsed("Budget Not Set For Category");
                analyticResponseDto.setBudgetRemaining(0.0);
            }
            else if(budgetAmount<expenseAmount)
            {
                analyticResponseDto.setBudgetPercentageUsed("Over Spending");
                analyticResponseDto.setBudgetRemaining(0.0);
            }
            else
            {
                Double percentage = expenseAmount/budgetAmount*100.00;
                String s = String.valueOf(percentage);
                analyticResponseDto.setBudgetPercentageUsed(s+"%");
                analyticResponseDto.setBudgetRemaining(budgetAmount-expenseAmount);
            }
            dto.add(analyticResponseDto);
        }
        return dto;
    }
}
