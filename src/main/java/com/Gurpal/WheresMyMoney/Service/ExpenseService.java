package com.Gurpal.WheresMyMoney.Service;

import com.Gurpal.WheresMyMoney.Dto.CategoryExpenseRequestDto;
import com.Gurpal.WheresMyMoney.Dto.ExpenseHistoryResponseDto;
import com.Gurpal.WheresMyMoney.Dto.ExpenseRequestDto;
import com.Gurpal.WheresMyMoney.Entity.Expense;
import com.Gurpal.WheresMyMoney.Entity.Income;
import com.Gurpal.WheresMyMoney.Entity.User;
import com.Gurpal.WheresMyMoney.Repository.ExpenseRepository;
import com.Gurpal.WheresMyMoney.Repository.IncomeRepository;
import com.Gurpal.WheresMyMoney.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    UserRepository userRepository;
    ExpenseRepository expenseRepository;
    IncomeRepository incomeRepository;

    @Autowired
    public ExpenseService(UserRepository userRepository, ExpenseRepository expenseRepository,IncomeRepository incomeRepository)
    {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.incomeRepository = incomeRepository;
    }


    public String addExpense(String userName, ExpenseRequestDto expenseRequestDto)
    {
        if(expenseRepository.getTotalExpense(userName)+expenseRequestDto.getExpenseAmount()>incomeRepository.getTotalIncome(userName))
        {
            return "Expenses Cannot Exceed Income";
        }
        Expense expense = new Expense();
        expense.setExpenseAmount(expenseRequestDto.getExpenseAmount());
        String desiredCategory = expenseRequestDto.getExpenseCategory().trim().toUpperCase();
        expense.setExpenseCategory(desiredCategory);
        User user = userRepository.findByUserName(userName).get();
        expense.setUser(user);
        expenseRepository.save(expense);
        user.getExpenses().add(expense);

        return "Expense Detail Added Successfully";
    }

    public List<CategoryExpenseRequestDto> getExpenseByCategory(String userName)
    {
        List<Object[]> list = expenseRepository.getCategoryWiseExpense(userName);
        List<CategoryExpenseRequestDto> dto = new ArrayList<>();

        for (Object obj[] : list)
        {
            CategoryExpenseRequestDto categoryExpenseRequestDto = new CategoryExpenseRequestDto();
            categoryExpenseRequestDto.setCategory((String) obj[0]);
            categoryExpenseRequestDto.setTotalExpense((Double) obj[1]);
            dto.add(categoryExpenseRequestDto);
        }
        return dto;
    }

    public List<ExpenseHistoryResponseDto> getExpenseHistory(String userName)
    {
        List<ExpenseHistoryResponseDto> dto = new ArrayList<>();
        List<Expense> list = userRepository.findByUserName(userName).get().getExpenses();

        for(Expense expense:list)
        {
            ExpenseHistoryResponseDto expenseHistoryResponseDto = new ExpenseHistoryResponseDto();
            expenseHistoryResponseDto.setExpenseId(expense.getExpenseId());
            expenseHistoryResponseDto.setExpenseAmount(expense.getExpenseAmount());
            expenseHistoryResponseDto.setExpenseCategory(expense.getExpenseCategory());
            expenseHistoryResponseDto.setCreatedAt(expense.getCreatedAt());
            dto.add(expenseHistoryResponseDto);
        }
        return dto;
    }

    public String deleteExpense(String userName,Long expenseId)
    {
        Optional<Expense> expense = expenseRepository.findById(expenseId);
        User user = userRepository.findByUserName(userName).get();

        if(expense.isPresent() && user.getExpenses().contains(expense.get()))
        {
            user.getExpenses().remove(expense.get());
            expenseRepository.delete(expense.get());
            return "Expense Detail Deleted Successfully";
        }
        else
        {
            return "Wrong Income Id";
        }
    }
}
