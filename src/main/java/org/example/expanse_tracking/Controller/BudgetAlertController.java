package org.example.expanse_tracking.Controller;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Response.ApiResponse;
import org.example.expanse_tracking.Dto.Response.BudgetAlertResponse;
import org.example.expanse_tracking.Dao.BudgetAlertDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetAlertController {

    private final BudgetAlertDao budgetAlertDao;

    @GetMapping("/{budgetId}/alert")
    public ResponseEntity<ApiResponse<BudgetAlertResponse>> getBudgetAlert(
            @PathVariable Long budgetId) {

        BudgetAlertResponse response =
                budgetAlertDao.getBudgetAlert(budgetId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        response,
                        HttpStatus.OK.value(),
                        "Budget alert retrieved successfully"
                )
        );
    }
}