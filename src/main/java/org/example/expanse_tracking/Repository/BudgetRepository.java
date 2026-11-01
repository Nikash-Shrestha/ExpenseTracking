package org.example.expanse_tracking.Repository;

import org.example.expanse_tracking.Entity.Budget;
import org.example.expanse_tracking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findByUserOrderByStartDateDesc(User user);

    Optional<Budget> findByIdAndUser(Long id, User user);
}