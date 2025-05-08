package ru.otus.thesis.service;

import ru.otus.thesis.rest.dto.StudentGroupsDto;

public interface StudentService {

    StudentGroupsDto getGroups(long studentId);

    void joinGroup(long studentId, long groupId);

    void leaveGroup(long studentId, long groupId);
}
