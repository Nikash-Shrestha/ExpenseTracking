
package org.example.expanse_tracking.Mapper;

import org.example.expanse_tracking.Dto.Request.IncomeRequest;
import org.example.expanse_tracking.Dto.Response.IncomeResponse;
import org.example.expanse_tracking.Entity.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IncomeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Income toEntity(IncomeRequest request);

    IncomeResponse toResponse(Income income);

    void updateEntity(
            IncomeRequest request,
            @MappingTarget Income income
    );
}
