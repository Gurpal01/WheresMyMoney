package com.Gurpal.WheresMyMoney.Repository;

import com.Gurpal.WheresMyMoney.Entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {
    @Query("Select COALESCE(sum(i.incomeAmount),0) from Income as i where i.user.userName = :userName")
    Double getTotalIncome(String userName);
}
