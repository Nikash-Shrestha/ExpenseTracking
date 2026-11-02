package org.example.expanse_tracking.Controller;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Response.ApiResponse;
import org.example.expanse_tracking.Dto.Response.DashboardSummaryResponse;
import org.example.expanse_tracking.Dao.DashboardDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardDao dashboardDao;

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<DashboardSummaryResponse>> getDashboardSummary() {

        DashboardSummaryResponse response =
                dashboardDao.getDashboardSummary();

        return ResponseEntity.ok(
                ApiResponse.success(
                        response,
                        HttpStatus.OK.value(),
                        "Dashboard summary retrieved successfully"
                )
        );
    }
}