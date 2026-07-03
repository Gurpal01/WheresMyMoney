package com.Gurpal.WheresMyMoney.Dto;

public class AnalyticResponseDto {
    private String Category;
    private Double budgetAmount;
    private Double expenseAmount;
    private String budgetPercentageUsed;
    private Double budgetRemaining;

    public Double getBudgetRemaining() {
        return budgetRemaining;
    }

    public void setBudgetRemaining(Double budgetRemaining) {
        this.budgetRemaining = budgetRemaining;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public Double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(Double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getBudgetPercentageUsed() {
        return budgetPercentageUsed;
    }

    public void setBudgetPercentageUsed(String budgetPercentageUsed) {
        this.budgetPercentageUsed = budgetPercentageUsed;
    }
}
