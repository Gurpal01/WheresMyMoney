package com.Gurpal.WheresMyMoney.Service;

import com.Gurpal.WheresMyMoney.Dto.DashboardResponseDto;
import com.Gurpal.WheresMyMoney.Repository.BudgetRepository;
import com.Gurpal.WheresMyMoney.Repository.ExpenseRepository;
import com.Gurpal.WheresMyMoney.Repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    IncomeRepository incomeRepository;
    ExpenseRepository expenseRepository;
    BudgetRepository budgetRepository;

    @Autowired
    public DashboardService(IncomeRepository incomeRepository, ExpenseRepository expenseRepository, BudgetRepository budgetRepository)
    {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
    }


    public DashboardResponseDto getDashBoard(String userName)
    {
        Double totalIncome = incomeRepository.getTotalIncome(userName);
        Double totalExpense = expenseRepository.getTotalExpense(userName);
        Double totalBudget = budgetRepository.getTotalBudget(userName);
        DashboardResponseDto dashboardResponseDto = new DashboardResponseDto();
        dashboardResponseDto.setTotalIncome(totalIncome);
        dashboardResponseDto.setTotalExpense(totalExpense);
        dashboardResponseDto.setTotalBudget(totalBudget);
        dashboardResponseDto.setRemainingBalance(totalIncome-totalExpense);
        dashboardResponseDto.setBudgetRemaining(totalBudget-totalExpense);
        if(totalBudget-totalExpense<0)
        {
            dashboardResponseDto.setRemarks("Overspending");
        }
        else if(totalBudget-totalExpense==0)
        {
            dashboardResponseDto.setRemarks("Fully Utilized Budget");
        }
        else
        {
            dashboardResponseDto.setRemarks("You are within budget");
        }

        return dashboardResponseDto;
    }


}
