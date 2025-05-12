package ru.otus.thesis.service;

import ru.otus.thesis.rest.dto.StudentGroupResponse;
import ru.otus.thesis.rest.dto.GroupsDto;

public interface GroupService {

    GroupsDto getGroups();

    StudentGroupResponse getLessons(long groupId);
}
