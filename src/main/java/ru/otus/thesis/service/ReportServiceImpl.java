package ru.otus.thesis.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.thesis.repository.ReportRepository;
import ru.otus.thesis.rest.dto.AverageDurationReportResponse;

@Slf4j
@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    @Transactional(readOnly = true)
    public AverageDurationReportResponse reportAverageDuration() {
        return new AverageDurationReportResponse()
                .setRows(reportRepository.reportAverageDuration());
    }
}
