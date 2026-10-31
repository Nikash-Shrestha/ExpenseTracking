
package org.example.expanse_tracking.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.example.expanse_tracking.Dao.IncomeDao;
import org.example.expanse_tracking.Dto.Request.IncomeRequest;
import org.example.expanse_tracking.Dto.Response.IncomeResponse;
import org.example.expanse_tracking.Dto.Response.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeDao incomeDao;

    // Add income
    @PostMapping
    public ResponseEntity<ApiResponse<IncomeResponse>> addIncome(
            @Valid @RequestBody IncomeRequest request
    ) {

        IncomeResponse response = incomeDao.addIncome(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        response,
                        HttpStatus.CREATED.value(),
                        "Income added successfully"
                ));
    }

    // Get all income records
    @GetMapping
    public ResponseEntity<ApiResponse<List<IncomeResponse>>> getAllIncome() {

        List<IncomeResponse> response = incomeDao.getAllIncome();

        return ResponseEntity.ok(
                ApiResponse.success(
                        response,
                        HttpStatus.OK.value(),
                        "Income records retrieved successfully"
                )
        );
    }

    // Get income by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IncomeResponse>> getIncomeById(
            @PathVariable Long id
    ) {

        IncomeResponse response = incomeDao.getIncomeById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        response,
                        HttpStatus.OK.value(),
                        "Income retrieved successfully"
                )
        );
    }

    // Update income
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IncomeResponse>> updateIncome(
            @PathVariable Long id,
            @Valid @RequestBody IncomeRequest request
    ) {

        IncomeResponse response = incomeDao.updateIncome(id, request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        response,
                        HttpStatus.OK.value(),
                        "Income updated successfully"
                )
        );
    }

    // Delete income
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteIncome(
            @PathVariable Long id
    ) {

        incomeDao.deleteIncome(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        null,
                        HttpStatus.OK.value(),
                        "Income deleted successfully"
                )
        );
    }
}
