package ru.otus.thesis.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.thesis.rest.dto.TeacherHomeworksRequest;
import ru.otus.thesis.rest.dto.TeacherHomeworksResponse;
import ru.otus.thesis.service.TeacherService;

@Slf4j
@RestController
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    // MVP: Список ДЗ студентов с оценками и статусами
    @PostMapping("/api/teacher/homeworks")
    public ResponseEntity<TeacherHomeworksResponse> getHomeworks(
            @RequestBody @Valid TeacherHomeworksRequest request
    ) {
        return ResponseEntity.ok()
                .body(teacherService.getHomeworks(request));
    }
}
