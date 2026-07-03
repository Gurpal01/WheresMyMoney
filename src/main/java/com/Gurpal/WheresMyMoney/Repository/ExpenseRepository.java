package com.Gurpal.WheresMyMoney.Repository;

import com.Gurpal.WheresMyMoney.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    @Query("""
    SELECT e.expenseCategory, SUM(e.expenseAmount)
    FROM Expense e
    WHERE e.user.userName = :userName
    GROUP BY e.expenseCategory
    """)
    List<Object[]> getCategoryWiseExpense(String userName);

    @Query("Select COALESCE(sum(e.expenseAmount),0) from Expense as e where e.user.userName = :userName")
    Double getTotalExpense(String userName);

}
