
package org.example.expanse_tracking.Repository;

import org.example.expanse_tracking.Entity.Expense;
import org.example.expanse_tracking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Get all expenses for a user, newest first
    List<Expense> findByUserOrderByDateDesc(User user);

    // Get one expense only if it belongs to the user
    Optional<Expense> findByIdAndUser(Long id, User user);
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.user = :user")
    BigDecimal calculateTotalExpenses(@Param("user") User user);
}
