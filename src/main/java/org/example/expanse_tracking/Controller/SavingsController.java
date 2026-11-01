package org.example.expanse_tracking.Controller;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dao.SavingsDao;
import org.example.expanse_tracking.Dto.Response.ApiResponse;
import org.example.expanse_tracking.Dto.Response.SavingsSummaryResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/savings")
@RequiredArgsConstructor
public class SavingsController {

    private final SavingsDao savingsDao;

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<SavingsSummaryResponse>> getSavingsSummary() {
        return ResponseEntity.ok(
                ApiResponse.success(
                        savingsDao.getSavingsSummary(),
                        HttpStatus.OK.value(),
                        "Savings summary retrieved successfully"
                )
        );
    }
}