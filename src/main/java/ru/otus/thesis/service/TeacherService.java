package ru.otus.thesis.service;

import ru.otus.thesis.rest.dto.TeacherHomeworksRequest;
import ru.otus.thesis.rest.dto.TeacherHomeworksResponse;

public interface TeacherService {

    TeacherHomeworksResponse getHomeworks(TeacherHomeworksRequest request);
}
