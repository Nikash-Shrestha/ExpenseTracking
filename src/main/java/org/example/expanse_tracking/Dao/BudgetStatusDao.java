package org.example.expanse_tracking.Dao;


import org.example.expanse_tracking.Dto.Response.BudgetStatusResponse;

public interface BudgetStatusDao {

    BudgetStatusResponse getBudgetStatus(Long budgetId);
}
