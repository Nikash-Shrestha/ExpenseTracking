package org.example.expanse_tracking.Dao;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Response.MonthlyReportResponse;
import org.example.expanse_tracking.Entity.Expense;
import org.example.expanse_tracking.Entity.Income;
import org.example.expanse_tracking.Entity.User;
import org.example.expanse_tracking.Exception.ResourceNotFoundException;
import org.example.expanse_tracking.Repository.ExpenseRepository;
import org.example.expanse_tracking.Repository.IncomeRepository;
import org.example.expanse_tracking.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonthlyReportDaoImpl implements MonthlyReportDao {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    @Override
    public MonthlyReportResponse getMonthlyReport(int month, int year) {

        if (month < 1 || month > 12 || year < 1) {
            throw new IllegalArgumentException("Invalid month or year");
        }

        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1);

        // Get monthly income records
        List<Income> incomes =
                incomeRepository.findByUserAndDateGreaterThanEqualAndDateLessThan(
                        user, startDate, endDate
                );

        // Get monthly expense records
        List<Expense> expenses =
                expenseRepository.findByUserAndDateGreaterThanEqualAndDateLessThan(
                        user, startDate, endDate
                );

        // Calculate total income
        BigDecimal totalIncome = incomes.stream()
                .map(Income::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calculate total expenses
        BigDecimal totalExpenses = expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calculate net savings
        BigDecimal netSavings = totalIncome.subtract(totalExpenses);

        // Calculate savings rate
        BigDecimal savingsRate = totalIncome.compareTo(BigDecimal.ZERO) > 0
                ? netSavings.multiply(BigDecimal.valueOf(100))
                .divide(totalIncome, 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        // Calculate category-wise expense breakdown
        Map<String, BigDecimal> expenseBreakdown = expenses.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Expense::getAmount,
                                BigDecimal::add
                        )
                ));

        return new MonthlyReportResponse(
                month,
                year,
                totalIncome,
                totalExpenses,
                netSavings,
                savingsRate,
                expenseBreakdown
        );
    }
}