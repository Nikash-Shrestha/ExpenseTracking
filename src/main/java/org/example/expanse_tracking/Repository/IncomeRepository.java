package org.example.expanse_tracking.Repository;

import org.example.expanse_tracking.Entity.Income;
import org.example.expanse_tracking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUserOrderByDateDesc(User user);
    Optional<Income> findByIdAndUser(Long id, User user);

}