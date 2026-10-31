
package org.example.expanse_tracking.Repository;

import org.example.expanse_tracking.Entity.Expense;
import org.example.expanse_tracking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Get all expenses for a user, newest first
    List<Expense> findByUserOrderByDateDesc(User user);

    // Get one expense only if it belongs to the user
    Optional<Expense> findByIdAndUser(Long id, User user);
}
