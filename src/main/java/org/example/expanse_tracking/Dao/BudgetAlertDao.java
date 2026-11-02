package org.example.expanse_tracking.Dao;

import org.example.expanse_tracking.Dto.Response.BudgetAlertResponse;

public interface BudgetAlertDao {

    BudgetAlertResponse getBudgetAlert(Long budgetId);
}