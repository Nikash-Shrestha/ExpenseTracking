package org.example.expanse_tracking.Dto.Response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BudgetResponse(
        Long id,
        BigDecimal amount,
        String category,
        LocalDate startDate,
        LocalDate endDate
) {}