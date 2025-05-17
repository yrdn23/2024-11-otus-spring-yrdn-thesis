package ru.otus.thesis.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.thesis.repository.ReportRepository;
import ru.otus.thesis.rest.dto.AverageDurationReportResponse;
import ru.otus.thesis.rest.dto.StudentProgressReportResponse;

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

    @Override
    public StudentProgressReportResponse reportStudentProgress() {
        return new StudentProgressReportResponse()
                .setRows(reportRepository.reportStudentProgress());
    }
}
