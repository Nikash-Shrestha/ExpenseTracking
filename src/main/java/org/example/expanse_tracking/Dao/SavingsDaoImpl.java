package org.example.expanse_tracking.Dao;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Response.SavingsSummaryResponse;
import org.example.expanse_tracking.Entity.User;
import org.example.expanse_tracking.Exception.ResourceNotFoundException;
import org.example.expanse_tracking.Repository.ExpenseRepository;
import org.example.expanse_tracking.Repository.IncomeRepository;
import org.example.expanse_tracking.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SavingsDaoImpl implements SavingsDao {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    @Override
    public SavingsSummaryResponse getSavingsSummary() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        BigDecimal totalIncome =
                incomeRepository.calculateTotalIncome(user);

        BigDecimal totalExpenses =
                expenseRepository.calculateTotalExpenses(user);

        BigDecimal savings = totalIncome.subtract(totalExpenses);

        return new SavingsSummaryResponse(
                totalIncome,
                totalExpenses,
                savings
        );
    }
}