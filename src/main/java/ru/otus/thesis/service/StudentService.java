package ru.otus.thesis.service;

import ru.otus.thesis.controller.dto.StudentGroupRequest;
import ru.otus.thesis.controller.dto.StudentGroupResponse;
import ru.otus.thesis.controller.dto.StudentHomeworksRequest;
import ru.otus.thesis.controller.dto.StudentHomeworksResponse;

public interface StudentService {

    StudentGroupResponse getGroup(StudentGroupRequest request);

    StudentHomeworksResponse getHomeworks(StudentHomeworksRequest request);

    void joinGroup(long studentId, long groupId);

    void leaveGroup(long studentId, long groupId);
}
