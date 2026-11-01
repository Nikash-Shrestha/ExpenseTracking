package org.example.expanse_tracking.Mapper;

import org.example.expanse_tracking.Dto.Request.BudgetRequest;
import org.example.expanse_tracking.Dto.Response.BudgetResponse;
import org.example.expanse_tracking.Entity.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Budget toEntity(BudgetRequest request);

    BudgetResponse toResponse(Budget budget);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntity(
            BudgetRequest request,
            @MappingTarget Budget budget
    );
}