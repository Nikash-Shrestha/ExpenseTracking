
package org.example.expanse_tracking.Dao;

import org.example.expanse_tracking.Dto.Request.IncomeRequest;
import org.example.expanse_tracking.Dto.Response.IncomeResponse;

import java.util.List;

public interface IncomeDao {

    IncomeResponse addIncome(IncomeRequest request);

    List<IncomeResponse> getAllIncome();

    IncomeResponse getIncomeById(Long id);

    IncomeResponse updateIncome(Long id, IncomeRequest request);

    void deleteIncome(Long id);
}
