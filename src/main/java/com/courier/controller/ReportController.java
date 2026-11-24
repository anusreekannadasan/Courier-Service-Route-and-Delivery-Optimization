package com.courier.controller;

import org.springframework.web.bind.annotation.*;
import com.courier.service.DeliveryReportService;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class ReportController {

    private final DeliveryReportService reportService;

    public ReportController(DeliveryReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/generate")
    public String generateReport() throws java.io.IOException {
        return reportService.generateReport();
    }
}