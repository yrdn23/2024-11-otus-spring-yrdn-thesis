package ru.otus.thesis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.thesis.enums.HomeworkStatus;
import ru.otus.thesis.exceptions.EntityNotFoundException;
import ru.otus.thesis.model.Homework;
import ru.otus.thesis.repository.HomeworkRepository;
import ru.otus.thesis.rest.dto.TeacherHomeworkAcceptRequest;
import ru.otus.thesis.rest.dto.TeacherHomeworksRequest;
import ru.otus.thesis.rest.dto.TeacherHomeworksResponse;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TeacherServiceImplTest {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private HomeworkRepository homeworkRepository;


    @Test
    void getHomeworksStudentNotFound() {
        TeacherHomeworksRequest request = new TeacherHomeworksRequest()
                .setTeacherId(-1000L)
                .setGroupId(-1000L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> teacherService.getHomeworks(request));

        assertEquals("Teacher not found with id: -1000", exception.getMessage());
    }

    @Test
    void getHomeworksGroupNotFound() {
        TeacherHomeworksRequest request = new TeacherHomeworksRequest()
                .setTeacherId(5L)
                .setGroupId(-1000L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> teacherService.getHomeworks(request));

        assertEquals("Group not found with id: -1000", exception.getMessage());
    }

    @Test
    @Transactional(readOnly = true)
    void getHomeworksSuccess() {
        TeacherHomeworksRequest request = new TeacherHomeworksRequest()
                .setTeacherId(5L)
                .setGroupId(1L);

        TeacherHomeworksResponse actualResult = teacherService.getHomeworks(request);

        assertEquals(5L, actualResult.getTeacherId());
        assertEquals(1L, actualResult.getGroupId());
        assertEquals(1L, actualResult.getCourseId());
        assertEquals(6, actualResult.getHomeworks().size());
        assertTrue(actualResult.getHomeworks().stream()
                .map(TeacherHomeworksResponse.HomeworkDto::getId)
                .collect(Collectors.toSet())
                .containsAll(Set.of(3L, 6L, 15L, 18L, 27L, 30L)));
    }

    @Test
    void acceptHomeworkTeacherNotFound() {
        TeacherHomeworkAcceptRequest request = new TeacherHomeworkAcceptRequest()
                .setTeacherId(-1000L)
                .setHomeworkId(-1000L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> teacherService.acceptHomework(request));

        assertEquals("Teacher not found with id: -1000", exception.getMessage());
    }

    @Test
    void acceptHomeworkHomeworkNotFound() {
        TeacherHomeworkAcceptRequest request = new TeacherHomeworkAcceptRequest()
                .setTeacherId(6L)
                .setHomeworkId(-1000L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> teacherService.acceptHomework(request));

        assertEquals("Homework not found with id: -1000", exception.getMessage());
    }

    @Test
    void acceptHomeworkSuccess() {
        TeacherHomeworkAcceptRequest request = new TeacherHomeworkAcceptRequest()
                .setTeacherId(6L)
                .setHomeworkId(17L)
                .setScore(10)
                .setComment("Test comment from acceptHomeworkSuccess")
                .setStatus(HomeworkStatus.ACCEPTED);

        teacherService.acceptHomework(request);

        Homework homework = homeworkRepository.findById(17L).get();

        assertEquals(17L, homework.getId());
        assertEquals(10, homework.getScore());
        assertEquals("Test comment from acceptHomeworkSuccess", homework.getComment());
        assertEquals(HomeworkStatus.ACCEPTED, homework.getStatus());
    }


}