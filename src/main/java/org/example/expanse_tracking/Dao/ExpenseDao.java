
package org.example.expanse_tracking.Dao;

import org.example.expanse_tracking.Dto.Request.ExpenseRequest;
import org.example.expanse_tracking.Dto.Response.ExpenseResponse;

import java.util.List;

public interface ExpenseDao {

    ExpenseResponse addExpense(ExpenseRequest request);

    List<ExpenseResponse> getAllExpenses();

    ExpenseResponse getExpenseById(Long id);

    ExpenseResponse updateExpense(Long id, ExpenseRequest request);

    void deleteExpense(Long id);
}
