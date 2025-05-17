package ru.otus.thesis.service;

import ru.otus.thesis.rest.dto.AverageDurationReportResponse;
import ru.otus.thesis.rest.dto.StudentProgressReportResponse;

public interface ReportService {

    AverageDurationReportResponse reportAverageDuration();

    StudentProgressReportResponse reportStudentProgress();
}
