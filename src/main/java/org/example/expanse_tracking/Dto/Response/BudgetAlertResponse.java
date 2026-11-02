package org.example.expanse_tracking.Dto.Response;

import java.math.BigDecimal;

public record BudgetAlertResponse(
        Long budgetId,
        String category,
        BigDecimal budgetAmount,
        BigDecimal spentAmount,
        BigDecimal percentageUsed,
        String alertLevel,
        String message
) {}