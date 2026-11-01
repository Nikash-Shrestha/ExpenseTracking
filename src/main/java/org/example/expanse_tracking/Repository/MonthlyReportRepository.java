package org.example.expanse_tracking.Repository;

import org.example.expanse_tracking.Entity.Expense;
import org.example.expanse_tracking.Entity.Income;
import org.example.expanse_tracking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface MonthlyReportRepository
        extends JpaRepository<Expense, Long> {

    @Query("""
        SELECT COALESCE(SUM(i.amount), 0)
        FROM Income i
        WHERE i.user = :user
          AND i.date >= :startDate
          AND i.date < :endDate
        """)
    BigDecimal calculateMonthlyIncome(
            @Param("user") User user,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
        SELECT COALESCE(SUM(e.amount), 0)
        FROM Expense e
        WHERE e.user = :user
          AND e.date >= :startDate
          AND e.date < :endDate
        """)
    BigDecimal calculateMonthlyExpenses(
            @Param("user") User user,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
        SELECT e.category, SUM(e.amount)
        FROM Expense e
        WHERE e.user = :user
          AND e.date >= :startDate
          AND e.date < :endDate
        GROUP BY e.category
        """)
    List<Object[]> calculateMonthlyExpenseBreakdown(
            @Param("user") User user,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}