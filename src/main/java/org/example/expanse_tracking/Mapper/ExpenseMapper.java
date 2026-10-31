
package org.example.expanse_tracking.Mapper;

import org.example.expanse_tracking.Dto.Request.ExpenseRequest;
import org.example.expanse_tracking.Dto.Response.ExpenseResponse;
import org.example.expanse_tracking.Entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Expense toEntity(ExpenseRequest request);

    ExpenseResponse toResponse(Expense expense);

    void updateEntity(
            ExpenseRequest request,
            @MappingTarget Expense expense
    );
}
