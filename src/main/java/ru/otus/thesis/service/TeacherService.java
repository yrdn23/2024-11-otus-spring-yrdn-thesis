package ru.otus.thesis.service;

import ru.otus.thesis.controller.dto.ResultResponse;
import ru.otus.thesis.controller.dto.TeacherHomeworkAcceptRequest;
import ru.otus.thesis.controller.dto.TeacherHomeworksRequest;
import ru.otus.thesis.controller.dto.TeacherHomeworksResponse;

public interface TeacherService {

    TeacherHomeworksResponse getHomeworks(TeacherHomeworksRequest request);

    ResultResponse acceptHomework(TeacherHomeworkAcceptRequest request);
}
