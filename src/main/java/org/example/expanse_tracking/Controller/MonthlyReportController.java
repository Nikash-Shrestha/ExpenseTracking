package org.example.expanse_tracking.Controller;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dao.MonthlyReportDao;
import org.example.expanse_tracking.Dto.Response.MonthlyReportResponse;
import org.example.expanse_tracking.Dto.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class MonthlyReportController {

    private final MonthlyReportDao monthlyReportDao;

    @GetMapping("/monthly")
    public ResponseEntity<ApiResponse<MonthlyReportResponse>> getMonthlyReport(
            @RequestParam int month,
            @RequestParam int year) {

        MonthlyReportResponse report =
                monthlyReportDao.getMonthlyReport(month, year);

        return ResponseEntity.ok(
                ApiResponse.success(
                        report,
                        HttpStatus.OK.value(),
                        "Monthly report retrieved successfully"
                )
        );
    }
}