package ru.otus.thesis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.thesis.exceptions.EntityNotFoundException;
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
    void getHomeworksNoStudent() {
        StudentHomeworksRequest request = new StudentHomeworksRequest()
                .setStudentId(-1000L)
                .setGroupId(-1000L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> studentService.getHomeworks(request));

        assertEquals("Student not found with id: -1000", exception.getMessage());
    }

    @Test
    void getHomeworksNoGroup() {
        StudentHomeworksRequest request = new StudentHomeworksRequest()
                .setStudentId(2L)
                .setGroupId(-1000L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> studentService.getHomeworks(request));

        assertEquals("Group not found with id: -1000", exception.getMessage());
    }

    @Test
    @Transactional(readOnly = true)
    void getHomeworksSuccess() {
        StudentHomeworksRequest request = new StudentHomeworksRequest()
                .setStudentId(2L)
                .setGroupId(2L);

        StudentHomeworksResponse actualResult = studentService.getHomeworks(request);

        assertEquals(2L, actualResult.getStudentId());
        assertEquals(2L, actualResult.getGroupId());
        assertEquals(1L, actualResult.getCourseId());
        assertEquals(3, actualResult.getHomeworks().size());
        assertTrue(actualResult.getHomeworks().stream()
                .map(StudentHomeworksResponse.HomeworkDto::getId)
                .collect(Collectors.toSet())
                .containsAll(Set.of(7L, 8L, 9L)));
    }
  
}