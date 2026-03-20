package ru.otus.thesis.service;

import ru.otus.thesis.controller.dto.StudentGroupResponse;
import ru.otus.thesis.controller.dto.GroupsDto;

public interface GroupService {

    GroupsDto getGroups();

    StudentGroupResponse getLessons(long groupId);
}
