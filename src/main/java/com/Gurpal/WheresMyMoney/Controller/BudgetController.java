package com.Gurpal.WheresMyMoney.Controller;

import com.Gurpal.WheresMyMoney.Dto.BudgetRequestDto;
import com.Gurpal.WheresMyMoney.Dto.BudgetResponseDto;
import com.Gurpal.WheresMyMoney.Service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService)
    {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<String> addBudget(@RequestBody  BudgetRequestDto budgetRequestDto, Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(budgetService.addBudget(userName,budgetRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<BudgetResponseDto>> getBudget(Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(budgetService.getBudget(userName));
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<String> deleteBudget(@PathVariable Long budgetId, Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(budgetService.deleteBudget(userName,budgetId));
    }
}
