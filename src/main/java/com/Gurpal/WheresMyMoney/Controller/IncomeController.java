package com.Gurpal.WheresMyMoney.Controller;

import com.Gurpal.WheresMyMoney.Dto.IncomeRequestDto;
import com.Gurpal.WheresMyMoney.Dto.IncomeResponseDto;
import com.Gurpal.WheresMyMoney.Service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {

    IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService)
    {
        this.incomeService = incomeService;
    }

    @PostMapping
    public ResponseEntity<String> addIncome(@RequestBody IncomeRequestDto incomeRequestDto, Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(incomeService.setIncome(incomeRequestDto,userName));
    }

    @GetMapping
    public ResponseEntity<List<IncomeResponseDto>> getIncome(Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(incomeService.getIncome(userName));
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<String> deleteIncome(@PathVariable Long incomeId, Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(incomeService.deleteIncome(userName,incomeId));
    }


}
