package ru.otus.thesis.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.thesis.exceptions.EntityNotFoundException;
import ru.otus.thesis.model.Group;
import ru.otus.thesis.model.Student;
import ru.otus.thesis.repository.GroupRepository;
import ru.otus.thesis.repository.StudentRepository;
import ru.otus.thesis.rest.dto.StudentGroupsDto;
import ru.otus.thesis.rest.dto.StudentHomeworksRequest;
import ru.otus.thesis.rest.dto.StudentHomeworksResponse;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private static final String STUDENT_NOT_FOUND = "Student not found with id: %d";

    private static final String GROUP_NOT_FOUND = "Group not found with id: %d";

    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;

    @Override
    public StudentGroupsDto getGroups(long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException(STUDENT_NOT_FOUND.formatted(studentId)));

        List<StudentGroupsDto.GroupDto> groups = student.getGroups().stream()
                .map(StudentGroupsDto::from)
                .toList();

        return StudentGroupsDto.from(student)
                .setGroups(groups);
    }

    @Override
    public StudentHomeworksResponse getHomeworks(StudentHomeworksRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException(STUDENT_NOT_FOUND.formatted(request.getStudentId())));
        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException(GROUP_NOT_FOUND.formatted(request.getGroupId())));

        List<StudentHomeworksResponse.HomeworkDto> homeworks = student.getHomeworks().stream()
                .filter(homework -> homework.getLesson().getGroup().getId() == group.getId())
                .map(StudentHomeworksResponse::from)
                .toList();

        return StudentHomeworksResponse.from(student, group)
                .setHomeworks(homeworks);
    }

    @Override
    @Transactional
    public void joinGroup(long studentId, long groupId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException(STUDENT_NOT_FOUND.formatted(studentId)));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException(GROUP_NOT_FOUND.formatted(groupId)));

        if (!group.getStudents().contains(student)) {
            group.getStudents().add(student);
        }

        groupRepository.save(group);
    }

    @Override
    public void leaveGroup(long studentId, long groupId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException(STUDENT_NOT_FOUND.formatted(studentId)));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException(GROUP_NOT_FOUND.formatted(groupId)));

        group.getStudents().remove(student);

        groupRepository.save(group);
    }
}
