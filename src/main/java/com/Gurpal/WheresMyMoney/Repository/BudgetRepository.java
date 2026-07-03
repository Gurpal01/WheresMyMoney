package com.Gurpal.WheresMyMoney.Repository;

import com.Gurpal.WheresMyMoney.Entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {

    @Query("Select COALESCE(sum(b.budgetAmount),0) from Budget as b where b.user.userName = :userName")
    Double getTotalBudget(String userName);


    @Query("""
    SELECT b.budgetCategory, COALESCE(SUM(b.budgetAmount), 0)
    FROM Budget b
    WHERE b.user.userName = :userName
    GROUP BY b.budgetCategory
    """)
    List<Object[]> getCategoryWiseBudget(String userName);

    @Query("""
    SELECT COALESCE(SUM(b.budgetAmount), 0)
    FROM Budget b
    WHERE b.user.userName = :userName
    AND b.budgetCategory = :budgetCategory
    """)
    Double getBudgetForCategory(String userName, String budgetCategory);
}
