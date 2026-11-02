package org.example.expanse_tracking.Dao;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Response.DashboardSummaryResponse;
import org.example.expanse_tracking.Entity.Budget;
import org.example.expanse_tracking.Entity.User;
import org.example.expanse_tracking.Exception.ResourceNotFoundException;
import org.example.expanse_tracking.Repository.BudgetRepository;
import org.example.expanse_tracking.Repository.ExpenseRepository;
import org.example.expanse_tracking.Repository.IncomeRepository;
import org.example.expanse_tracking.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardDaoImpl implements DashboardDao {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final BudgetStatusDao budgetStatusDao;

    @Override
    public DashboardSummaryResponse getDashboardSummary() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        BigDecimal totalIncome =
                incomeRepository.calculateTotalIncome(user);

        BigDecimal totalExpenses =
                expenseRepository.calculateTotalExpenses(user);

        BigDecimal savings = totalIncome.subtract(totalExpenses);

        List<Budget> budgets = budgetRepository.findByUserOrderByStartDateDesc(user);

        long exceededBudgets = budgets.stream()
                .filter(budget ->
                        budgetStatusDao.getBudgetStatus(budget.getId())
                                .percentageUsed()
                                .compareTo(BigDecimal.valueOf(100)) >= 0)
                .count();

        return new DashboardSummaryResponse(
                totalIncome,
                totalExpenses,
                savings,
                budgets.size(),
                exceededBudgets
        );
    }
}