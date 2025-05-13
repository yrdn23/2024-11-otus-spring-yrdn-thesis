package ru.otus.thesis.service;

import ru.otus.thesis.rest.dto.ResultResponse;
import ru.otus.thesis.rest.dto.StudentGroupRequest;
import ru.otus.thesis.rest.dto.StudentGroupResponse;
import ru.otus.thesis.rest.dto.StudentHomeworksRequest;
import ru.otus.thesis.rest.dto.StudentHomeworksResponse;
import ru.otus.thesis.rest.dto.StudentMessageSendRequest;
import ru.otus.thesis.rest.dto.StudentMessagesRequest;
import ru.otus.thesis.rest.dto.StudentMessagesResponse;

public interface StudentService {

    StudentGroupResponse getGroup(StudentGroupRequest request);

    StudentHomeworksResponse getHomeworks(StudentHomeworksRequest request);

    StudentMessagesResponse getMessages(StudentMessagesRequest request);

    ResultResponse sendMessage(StudentMessageSendRequest request);

    void joinGroup(long studentId, long groupId);

    void leaveGroup(long studentId, long groupId);
}
