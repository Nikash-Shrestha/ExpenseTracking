package org.example.expanse_tracking.Repository;

import org.example.expanse_tracking.Entity.Income;
import org.example.expanse_tracking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUserOrderByDateDesc(User user);
    Optional<Income> findByIdAndUser(Long id, User user);
    @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Income i WHERE i.user = :user")
    BigDecimal calculateTotalIncome(@Param("user") User user);

}