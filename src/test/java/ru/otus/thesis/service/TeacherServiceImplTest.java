package ru.otus.thesis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.thesis.exceptions.EntityNotFoundException;
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
}