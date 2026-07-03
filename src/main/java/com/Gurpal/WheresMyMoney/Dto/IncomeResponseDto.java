package com.Gurpal.WheresMyMoney.Dto;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public class IncomeResponseDto {
    private Long incomeId;
    private double incomeAmount;
    private String incomeSource;
    private LocalDateTime createdAt;

    public Long getIncomeId() {
        return incomeId;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public String getIncomeSource() {
        return incomeSource;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }

    public void setIncomeAmount(double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
