package com.Gurpal.WheresMyMoney.Dto;

public class DashboardResponseDto {

    private Double totalIncome;
    private Double totalExpense;
    private Double totalBudget;
    private Double remainingBalance;
    private Double budgetRemaining;
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Double getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(Double remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public Double getBudgetRemaining() {
        return budgetRemaining;
    }

    public void setBudgetRemaining(Double budgetRemaining) {
        this.budgetRemaining = budgetRemaining;
    }
}
