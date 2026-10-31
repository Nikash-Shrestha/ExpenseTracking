package org.example.expanse_tracking.Dto.Response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record IncomeResponse(
        Long id,
        BigDecimal amount,
        String source,
        String description,
        LocalDate date
) {}