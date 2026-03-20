package ru.otus.thesis.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.thesis.repository.GroupRepository;
import ru.otus.thesis.controller.dto.StudentGroupResponse;
import ru.otus.thesis.controller.dto.GroupsDto;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public GroupsDto getGroups() {
        List<GroupsDto.GroupDto> groups = groupRepository.findAll().stream()
                .map(GroupsDto::from)
                .toList();

        return new GroupsDto()
                .setGroups(groups);
    }

    @Override
    public StudentGroupResponse getLessons(long groupId) {
        List<StudentGroupResponse.LessonDto> lessons = null;

        return new StudentGroupResponse();
    }
}
