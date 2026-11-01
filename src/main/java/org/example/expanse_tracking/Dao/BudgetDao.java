package org.example.expanse_tracking.Dao;

import org.example.expanse_tracking.Dto.Request.BudgetRequest;
import org.example.expanse_tracking.Dto.Response.BudgetResponse;

import java.util.List;

public interface BudgetDao {

    BudgetResponse addBudget(BudgetRequest request);

    List<BudgetResponse> getAllBudgets();

    BudgetResponse getBudgetById(Long id);

    BudgetResponse updateBudget(Long id, BudgetRequest request);

    void deleteBudget(Long id);
}