
package org.example.expanse_tracking.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.example.expanse_tracking.Dao.ExpenseDao;
import org.example.expanse_tracking.Dto.Request.ExpenseRequest;
import org.example.expanse_tracking.Dto.Response.ExpenseResponse;
import org.example.expanse_tracking.Dto.Response.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseDao expenseDao;

    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseResponse>> addExpense(
            @Valid @RequestBody ExpenseRequest request) {

        ExpenseResponse response = expenseDao.addExpense(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        response,
                        HttpStatus.CREATED.value(),
                        "Expense added successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getAllExpenses() {
        List<ExpenseResponse> response = expenseDao.getAllExpenses();

        return ResponseEntity.ok(ApiResponse.success(
                response,
                HttpStatus.OK.value(),
                "Expenses retrieved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponse>> getExpenseById(
            @PathVariable Long id) {

        ExpenseResponse response = expenseDao.getExpenseById(id);

        return ResponseEntity.ok(ApiResponse.success(
                response,
                HttpStatus.OK.value(),
                "Expense retrieved successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponse>> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequest request) {

        ExpenseResponse response = expenseDao.updateExpense(id, request);

        return ResponseEntity.ok(ApiResponse.success(
                response,
                HttpStatus.OK.value(),
                "Expense updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteExpense(
            @PathVariable Long id) {

        expenseDao.deleteExpense(id);

        return ResponseEntity.ok(ApiResponse.success(
                null,
                HttpStatus.OK.value(),
                "Expense deleted successfully"));
    }
}
