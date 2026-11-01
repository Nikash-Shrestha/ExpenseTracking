package org.example.expanse_tracking.Dto.Response;

import java.math.BigDecimal;
import java.util.Map;

public record MonthlyReportResponse(
        int month,
        int year,
        BigDecimal totalIncome,
        BigDecimal totalExpenses,
        BigDecimal netSavings,
        BigDecimal savingsRate,
        Map<String, BigDecimal> expenseBreakdown
) {}