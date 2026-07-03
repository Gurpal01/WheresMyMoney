package com.Gurpal.WheresMyMoney.Controller;

import com.Gurpal.WheresMyMoney.Dto.CategoryExpenseRequestDto;
import com.Gurpal.WheresMyMoney.Dto.ExpenseHistoryResponseDto;
import com.Gurpal.WheresMyMoney.Dto.ExpenseRequestDto;
import com.Gurpal.WheresMyMoney.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService)
    {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<String> addExpense(@RequestBody ExpenseRequestDto expenseRequestDto, Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(expenseService.addExpense(userName,expenseRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryExpenseRequestDto>> getExpenseCategoryWise(Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(expenseService.getExpenseByCategory(userName));
    }
    @GetMapping("/history")
    public ResponseEntity<List<ExpenseHistoryResponseDto>> getExpenseHistory(Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(expenseService.getExpenseHistory(userName));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId, Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(expenseService.deleteExpense(userName,expenseId));
    }

}
