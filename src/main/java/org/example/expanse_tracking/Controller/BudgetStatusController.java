package org.example.expanse_tracking.Controller;
import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Response.ApiResponse;
import org.example.expanse_tracking.Dto.Response.BudgetStatusResponse;
import org.example.expanse_tracking.Dao.BudgetStatusDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetStatusController {

    private final BudgetStatusDao budgetStatusDao;

    @GetMapping("/{budgetId}/status")
    public ResponseEntity<ApiResponse<BudgetStatusResponse>> getBudgetStatus(
            @PathVariable Long budgetId) {

        BudgetStatusResponse response =
                budgetStatusDao.getBudgetStatus(budgetId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        response,
                        HttpStatus.OK.value(),
                        "Budget status retrieved successfully"
                )
        );
    }
}
