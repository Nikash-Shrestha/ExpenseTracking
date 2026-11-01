package org.example.expanse_tracking.Dto.Response;

import java.math.BigDecimal;

public record SavingsSummaryResponse(
        BigDecimal totalIncome,
        BigDecimal totalExpenses,
        BigDecimal savings
) {}