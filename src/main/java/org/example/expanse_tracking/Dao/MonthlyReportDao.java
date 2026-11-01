package org.example.expanse_tracking.Dao;

import org.example.expanse_tracking.Dto.Response.MonthlyReportResponse;

public interface MonthlyReportDao {

    MonthlyReportResponse getMonthlyReport(int month, int year);
}