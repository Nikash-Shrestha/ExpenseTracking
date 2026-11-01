package org.example.expanse_tracking.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dao.BudgetDao;
import org.example.expanse_tracking.Dto.Request.BudgetRequest;
import org.example.expanse_tracking.Dto.Response.BudgetResponse;
import org.example.expanse_tracking.Dto.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetDao budgetDao;

    @PostMapping
    public ResponseEntity<ApiResponse<BudgetResponse>> addBudget(
            @Valid @RequestBody BudgetRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(
                        budgetDao.addBudget(request),
                        HttpStatus.CREATED.value(),
                        "Budget created successfully"
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BudgetResponse>>> getAllBudgets() {
        return ResponseEntity.ok(
                ApiResponse.success(
                        budgetDao.getAllBudgets(),
                        HttpStatus.OK.value(),
                        "Budgets retrieved successfully"
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BudgetResponse>> getBudgetById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        budgetDao.getBudgetById(id),
                        HttpStatus.OK.value(),
                        "Budget retrieved successfully"
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BudgetResponse>> updateBudget(
            @PathVariable Long id,
            @Valid @RequestBody BudgetRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        budgetDao.updateBudget(id, request),
                        HttpStatus.OK.value(),
                        "Budget updated successfully"
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBudget(
            @PathVariable Long id) {

        budgetDao.deleteBudget(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        null,
                        HttpStatus.OK.value(),
                        "Budget deleted successfully"
                )
        );
    }
}