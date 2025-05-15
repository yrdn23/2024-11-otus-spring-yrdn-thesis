package ru.otus.thesis.rest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.thesis.enums.MessageDirection;
import ru.otus.thesis.rest.dto.MessageSendRequest;
import ru.otus.thesis.rest.dto.ResultResponse;
import ru.otus.thesis.rest.dto.TeacherHomeworkAcceptRequest;
import ru.otus.thesis.rest.dto.TeacherHomeworksRequest;
import ru.otus.thesis.rest.dto.TeacherHomeworksResponse;
import ru.otus.thesis.service.MessageService;
import ru.otus.thesis.service.TeacherService;

@Slf4j
@RestController
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    private final MessageService messageService;

    @Operation(summary = "MVP: Список ДЗ студентов с оценками и статусами")
    @PostMapping("/api/teacher/homeworks")
    public ResponseEntity<TeacherHomeworksResponse> getHomeworks(
            @RequestBody @Valid TeacherHomeworksRequest request
    ) {
        return ResponseEntity.ok()
                .body(teacherService.getHomeworks(request));
    }

    @Operation(summary = "Возможность принять ДЗ и поставить оценку")
    @PostMapping("/api/teacher/homework/accept")
    public ResponseEntity<ResultResponse> acceptHomework(
            @RequestBody @Valid TeacherHomeworkAcceptRequest request
    ) {
        return ResponseEntity.ok()
                .body(teacherService.acceptHomework(request));
    }

    @Operation(summary = "MVP: Возможность переписываться со студентом")
    @PostMapping("/api/teacher/message/send")
    public ResponseEntity<ResultResponse> sendMessage(
            @RequestBody @Valid MessageSendRequest request
    ) {
        return ResponseEntity.ok()
                .body(messageService.sendMessage(request, MessageDirection.T2S));
    }

}
