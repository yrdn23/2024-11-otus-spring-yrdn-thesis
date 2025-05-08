package ru.otus.thesis.service;

import ru.otus.thesis.rest.dto.GroupLessonsDto;
import ru.otus.thesis.rest.dto.GroupsDto;

public interface GroupService {

    GroupsDto getGroups();

    GroupLessonsDto getLessons(long groupId);
}
