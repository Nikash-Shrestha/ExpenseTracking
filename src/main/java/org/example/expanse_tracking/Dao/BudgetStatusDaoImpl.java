package org.example.expanse_tracking.Dao;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Response.BudgetStatusResponse;
import org.example.expanse_tracking.Entity.Budget;
import org.example.expanse_tracking.Entity.Expense;
import org.example.expanse_tracking.Entity.User;
import org.example.expanse_tracking.Exception.ResourceNotFoundException;
import org.example.expanse_tracking.Mapper.BudgetStatusMapper;
import org.example.expanse_tracking.Repository.BudgetRepository;
import org.example.expanse_tracking.Repository.ExpenseRepository;
import org.example.expanse_tracking.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetStatusDaoImpl implements BudgetStatusDao {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final BudgetStatusMapper budgetStatusMapper;

    @Override
    public BudgetStatusResponse getBudgetStatus(Long budgetId) {

        // Get the logged-in user
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        // Find this user's budget
        Budget budget = budgetRepository.findByIdAndUser(budgetId, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found"));

        // Get expenses in the budget category and date range
        List<Expense> expenses =
                expenseRepository.findByUserAndCategoryIgnoreCaseAndDateBetween(
                        user,
                        budget.getCategory(),
                        budget.getStartDate(),
                        budget.getEndDate()
                );

        // Calculate total spent
        BigDecimal spentAmount = expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calculate remaining amount
        BigDecimal remainingAmount =
                budget.getAmount().subtract(spentAmount);

        // Calculate percentage used
        BigDecimal percentageUsed = spentAmount
                .multiply(BigDecimal.valueOf(100))
                .divide(budget.getAmount(), 2, RoundingMode.HALF_UP);

        return budgetStatusMapper.toResponse(
                budget,
                spentAmount,
                remainingAmount,
                percentageUsed
        );
    }
}