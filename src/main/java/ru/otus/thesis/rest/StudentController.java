package ru.otus.thesis.rest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.thesis.enums.MessageDirection;
import ru.otus.thesis.rest.dto.MessageSendRequest;
import ru.otus.thesis.rest.dto.ResultResponse;
import ru.otus.thesis.rest.dto.StudentGroupRequest;
import ru.otus.thesis.rest.dto.StudentGroupResponse;
import ru.otus.thesis.rest.dto.StudentHomeworksRequest;
import ru.otus.thesis.rest.dto.StudentHomeworksResponse;
import ru.otus.thesis.service.MessageService;
import ru.otus.thesis.service.StudentService;

@Slf4j
@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    private final MessageService messageService;

    @Operation(summary = "MVP: Информация о группе")
    @PostMapping("/api/student/group")
    public ResponseEntity<StudentGroupResponse> getGroups(
            @RequestBody @Valid StudentGroupRequest request
    ) {
        return ResponseEntity.ok()
                .body(studentService.getGroup(request));
    }

    @Operation(summary = "MVP: Список ДЗ с оценками и статусами")
    @PostMapping("/api/student/homeworks")
    public ResponseEntity<StudentHomeworksResponse> getHomeworks(
            @RequestBody @Valid StudentHomeworksRequest request
    ) {
        return ResponseEntity.ok()
                .body(studentService.getHomeworks(request));
    }

    @Operation(summary = "MVP: Возможность переписываться с преподавателем")
    @PostMapping("/api/student/message/send")
    public ResponseEntity<ResultResponse> sendMessage(
            @RequestBody @Valid MessageSendRequest request
    ) {
        return ResponseEntity.ok()
                .body(messageService.sendMessage(request, MessageDirection.S2T));
    }

    @PostMapping("/api/student/{id}/group/{group_id}")
    public ResponseEntity<ResultResponse> joinGroup(
            @PathVariable("id") long studentId,
            @PathVariable("group_id") long groupId
    ) {
        studentService.joinGroup(studentId, groupId);
        return ResponseEntity.ok()
                .body(ResultResponse.OK);
    }

    @DeleteMapping("/api/student/{id}/group/{group_id}")
    public ResponseEntity<ResultResponse> leaveGroup(
            @PathVariable("id") long studentId,
            @PathVariable("group_id") long groupId
    ) {
        studentService.leaveGroup(studentId, groupId);
        return ResponseEntity.ok()
                .body(ResultResponse.OK);
    }
}
