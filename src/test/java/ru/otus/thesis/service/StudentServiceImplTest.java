package ru.otus.thesis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.thesis.exceptions.EntityNotFoundException;
import ru.otus.thesis.exceptions.InconsistentDataException;
import ru.otus.thesis.rest.dto.StudentGroupRequest;
import ru.otus.thesis.rest.dto.StudentGroupResponse;
import ru.otus.thesis.rest.dto.StudentHomeworksRequest;
import ru.otus.thesis.rest.dto.StudentHomeworksResponse;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    @Test
    void getHomeworksStudentNotFound() {
        StudentHomeworksRequest request = new StudentHomeworksRequest()
                .setStudentId(-1000L)
                .setGroupId(-1000L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> studentService.getHomeworks(request));

        assertEquals("Student not found with id: -1000", exception.getMessage());
    }

    @Test
    void getHomeworksGroupNotFound() {
        StudentHomeworksRequest request = new StudentHomeworksRequest()
                .setStudentId(2L)
                .setGroupId(-1000L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> studentService.getHomeworks(request));

        assertEquals("Group not found with id: -1000", exception.getMessage());
    }

    @Test
    @Transactional(readOnly = true)
    void getHomeworksStudentIsNotInGroup() {
        StudentHomeworksRequest request = new StudentHomeworksRequest()
                .setStudentId(3L)
                .setGroupId(3L);

        InconsistentDataException exception = assertThrows(InconsistentDataException.class,
                () -> studentService.getHomeworks(request));

        assertEquals("Student 3 is not in group 3", exception.getMessage());
    }

    @Test
    @Transactional(readOnly = true)
    void getHomeworksSuccess() {
        StudentHomeworksRequest request = new StudentHomeworksRequest()
                .setStudentId(3L)
                .setGroupId(2L);

        StudentHomeworksResponse actualResult = studentService.getHomeworks(request);

        assertEquals(3L, actualResult.getStudentId());
        assertEquals(2L, actualResult.getGroupId());
        assertEquals(1L, actualResult.getCourseId());
        assertEquals(3, actualResult.getHomeworks().size());
        assertTrue(actualResult.getHomeworks().stream()
                .map(StudentHomeworksResponse.HomeworkDto::getId)
                .collect(Collectors.toSet())
                .containsAll(Set.of(19L, 20L, 21L)));
    }

    @Test
    void getGroupStudentNotFound() {
        StudentGroupRequest request = new StudentGroupRequest()
                .setStudentId(-1000L)
                .setGroupId(-1000L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> studentService.getGroup(request));

        assertEquals("Student not found with id: -1000", exception.getMessage());
    }

    @Test
    void getGroupGroupNotFound() {
        StudentGroupRequest request = new StudentGroupRequest()
                .setStudentId(2L)
                .setGroupId(-1000L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> studentService.getGroup(request));

        assertEquals("Group not found with id: -1000", exception.getMessage());
    }

    @Test
    @Transactional(readOnly = true)
    void getGroupStudentIsNotInGroup() {
        StudentGroupRequest request = new StudentGroupRequest()
                .setStudentId(3L)
                .setGroupId(3L);

        InconsistentDataException exception = assertThrows(InconsistentDataException.class,
                () -> studentService.getGroup(request));

        assertEquals("Student 3 is not in group 3", exception.getMessage());
    }

    @Test
    @Transactional(readOnly = true)
    void getGroupSuccess() {
        StudentGroupRequest request = new StudentGroupRequest()
                .setStudentId(2L)
                .setGroupId(2L);

        StudentGroupResponse actualResult = studentService.getGroup(request);

        assertEquals(2L, actualResult.getId());
        assertEquals(1L, actualResult.getCourseId());
        assertEquals(3, actualResult.getLessons().size());
        assertTrue(actualResult.getLessons().stream()
                .map(StudentGroupResponse.LessonDto::getId)
                .collect(Collectors.toSet())
                .containsAll(Set.of(7L, 8L, 9L)));
    }
}