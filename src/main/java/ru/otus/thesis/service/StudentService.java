package ru.otus.thesis.service;

import ru.otus.thesis.rest.dto.StudentGroupRequest;
import ru.otus.thesis.rest.dto.StudentGroupResponse;
import ru.otus.thesis.rest.dto.StudentHomeworksRequest;
import ru.otus.thesis.rest.dto.StudentHomeworksResponse;

public interface StudentService {

    StudentGroupResponse getGroup(StudentGroupRequest request);

    StudentHomeworksResponse getHomeworks(StudentHomeworksRequest request);

    void joinGroup(long studentId, long groupId);

    void leaveGroup(long studentId, long groupId);
}
