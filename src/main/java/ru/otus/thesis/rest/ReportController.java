package ru.otus.thesis.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.thesis.rest.dto.AverageDurationReportResponse;
import ru.otus.thesis.service.ReportService;

@Slf4j
@RestController
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "MVP: Отчет - Самые долго сдающиеся ДЗ")
    @PostMapping("/api/report/average_duration")
    public ResponseEntity<AverageDurationReportResponse> reportAverageDuration() {
        return ResponseEntity.ok()
                .body(reportService.reportAverageDuration());
    }
}
