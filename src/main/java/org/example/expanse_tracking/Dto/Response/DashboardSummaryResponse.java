package org.example.expanse_tracking.Dto.Response;

import java.math.BigDecimal;

public record DashboardSummaryResponse(
        BigDecimal totalIncome,
        BigDecimal totalExpenses,
        BigDecimal savings,
        long totalBudgets,
        long exceededBudgets
) {}