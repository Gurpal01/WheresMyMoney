package com.Gurpal.WheresMyMoney.Dto;

public class CategoryExpenseRequestDto {
    private String category;
    private Double totalExpense;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }
}
