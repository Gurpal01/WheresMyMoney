package com.Gurpal.WheresMyMoney.Service;

import com.Gurpal.WheresMyMoney.Dto.IncomeRequestDto;
import com.Gurpal.WheresMyMoney.Dto.IncomeResponseDto;
import com.Gurpal.WheresMyMoney.Entity.Income;
import com.Gurpal.WheresMyMoney.Entity.User;
import com.Gurpal.WheresMyMoney.Repository.IncomeRepository;
import com.Gurpal.WheresMyMoney.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    UserRepository userRepository;
    IncomeRepository incomeRepository;

    @Autowired
    public IncomeService(UserRepository userRepository, IncomeRepository incomeRepository)
    {
        this.userRepository = userRepository;
        this.incomeRepository = incomeRepository;
    }

    public String setIncome(IncomeRequestDto incomeRequestDto,String userName)
    {
        Income income = new Income();
        income.setIncomeAmount(incomeRequestDto.getIncomeAmount());
        String desiredSource = incomeRequestDto.getIncomeSource().trim().toUpperCase();
        income.setIncomeSource(desiredSource);
        User user = userRepository.findByUserName(userName).get();
        income.setUser(user);
        user.getIncomes().add(income);
        incomeRepository.save(income);
        return "Income Details Added Successfully";
    }

    public List<IncomeResponseDto> getIncome(String userName)
    {
        List<Income> Incomes = userRepository.findByUserName(userName).get().getIncomes();
        List<IncomeResponseDto> dto = new ArrayList<>();
        for(Income income: Incomes)
        {
            IncomeResponseDto incomeResponseDto = new IncomeResponseDto();
            incomeResponseDto.setIncomeId(income.getIncomeId());
            incomeResponseDto.setIncomeAmount(income.getIncomeAmount());
            incomeResponseDto.setIncomeSource(income.getIncomeSource());
            incomeResponseDto.setCreatedAt(income.getCreatedAt());
            dto.add(incomeResponseDto);
        }

        return dto;
    }

    public String deleteIncome(String userName,Long incomeId)
    {
        Optional<Income> income = incomeRepository.findById(incomeId);
        User user = userRepository.findByUserName(userName).get();

        if(income.isPresent() && user.getIncomes().contains(income.get()))
        {
            user.getIncomes().remove(income.get());
            incomeRepository.delete(income.get());
            return "Income Detail Deleted Successfully";
        }
        else
        {
             return "Wrong Income Id";
        }
    }
}
