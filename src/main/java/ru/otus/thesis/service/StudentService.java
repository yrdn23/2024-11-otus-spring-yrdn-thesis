package ru.otus.thesis.service;

import ru.otus.thesis.rest.dto.StudentGroupsDto;
import ru.otus.thesis.rest.dto.StudentHomeworksRequest;
import ru.otus.thesis.rest.dto.StudentHomeworksResponse;

public interface StudentService {

    StudentGroupsDto getGroups(long studentId);

    StudentHomeworksResponse getHomeworks(StudentHomeworksRequest request);

    void joinGroup(long studentId, long groupId);

    void leaveGroup(long studentId, long groupId);
}
