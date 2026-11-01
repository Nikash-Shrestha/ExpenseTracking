package org.example.expanse_tracking.Dto.Response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BudgetStatusResponse(
        Long budgetId,
        String category,
        BigDecimal budgetAmount,
        BigDecimal spentAmount,
        BigDecimal remainingAmount,
        BigDecimal percentageUsed,
        LocalDate startDate,
        LocalDate endDate
) {}