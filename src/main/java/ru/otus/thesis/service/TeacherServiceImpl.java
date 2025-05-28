package ru.otus.thesis.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.thesis.exceptions.EntityNotFoundException;
import ru.otus.thesis.model.Group;
import ru.otus.thesis.model.Homework;
import ru.otus.thesis.model.Lesson;
import ru.otus.thesis.model.Teacher;
import ru.otus.thesis.repository.GroupRepository;
import ru.otus.thesis.repository.HomeworkRepository;
import ru.otus.thesis.repository.TeacherRepository;
import ru.otus.thesis.controller.dto.ResultResponse;
import ru.otus.thesis.controller.dto.TeacherHomeworkAcceptRequest;
import ru.otus.thesis.controller.dto.TeacherHomeworksRequest;
import ru.otus.thesis.controller.dto.TeacherHomeworksResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private static final String TEACHER_NOT_FOUND = "Teacher not found with id: %d";

    private static final String GROUP_NOT_FOUND = "Group not found with id: %d";

    private static final String HOMEWORK_NOT_FOUND = "Homework not found with id: %d";

    private final TeacherRepository teacherRepository;

    private final GroupRepository groupRepository;

    private final HomeworkRepository homeworkRepository;

    private final ValidationService validationService;

    @Override
    public TeacherHomeworksResponse getHomeworks(TeacherHomeworksRequest request) {
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException(TEACHER_NOT_FOUND.formatted(request.getTeacherId())));
        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException(GROUP_NOT_FOUND.formatted(request.getGroupId())));

        List<TeacherHomeworksResponse.HomeworkDto> homeworks = group.getLessons().stream()
                .map(Lesson::getHomeworks)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .map(TeacherHomeworksResponse::from)
                .filter(homework -> homework.getTeacherId() == teacher.getId())
                .toList();

        return TeacherHomeworksResponse.from(teacher, group)
                .setHomeworks(homeworks);
    }

    @Override
    @Transactional
    public ResultResponse acceptHomework(TeacherHomeworkAcceptRequest request) {
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException(TEACHER_NOT_FOUND.formatted(request.getTeacherId())));
        Homework homework = homeworkRepository.findById(request.getHomeworkId())
                .orElseThrow(() -> new EntityNotFoundException(HOMEWORK_NOT_FOUND.formatted(request.getHomeworkId())));

        validationService.validateHomeworkOfTeacher(teacher, homework);

        homework
                .setScore(request.getScore())
                .setComment(request.getComment())
                .setStatus(request.getStatus())
                .setSubmitDate(LocalDateTime.now());

        homeworkRepository.save(homework);

        return ResultResponse.OK;
    }
}
