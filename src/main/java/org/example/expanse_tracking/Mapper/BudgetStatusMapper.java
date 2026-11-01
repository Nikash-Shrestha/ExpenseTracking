package org.example.expanse_tracking.Mapper;

import org.example.expanse_tracking.Dto.Response.BudgetStatusResponse;
import org.example.expanse_tracking.Entity.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface BudgetStatusMapper {

    @Mapping(target = "budgetId", source = "budget.id")
    @Mapping(target = "category", source = "budget.category")
    @Mapping(target = "budgetAmount", source = "budget.amount")
    @Mapping(target = "startDate", source = "budget.startDate")
    @Mapping(target = "endDate", source = "budget.endDate")
    @Mapping(target = "spentAmount", source = "spentAmount")
    @Mapping(target = "remainingAmount", source = "remainingAmount")
    @Mapping(target = "percentageUsed", source = "percentageUsed")
    BudgetStatusResponse toResponse(
            Budget budget,
            BigDecimal spentAmount,
            BigDecimal remainingAmount,
            BigDecimal percentageUsed
    );
}