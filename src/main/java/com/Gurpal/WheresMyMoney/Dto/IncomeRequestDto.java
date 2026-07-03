package com.Gurpal.WheresMyMoney.Dto;

public class IncomeRequestDto {
    private double incomeAmount;
    private String incomeSource;

    public String getIncomeSource() {
        return incomeSource;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }
}
