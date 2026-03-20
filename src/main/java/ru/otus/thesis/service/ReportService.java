package ru.otus.thesis.service;

import ru.otus.thesis.controller.dto.AverageDurationReportResponse;
import ru.otus.thesis.controller.dto.StudentProgressReportResponse;

public interface ReportService {

    AverageDurationReportResponse reportAverageDuration();

    StudentProgressReportResponse reportStudentProgress();
}
