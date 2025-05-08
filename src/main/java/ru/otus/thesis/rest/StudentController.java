package ru.otus.thesis.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.thesis.rest.dto.ResultDto;
import ru.otus.thesis.rest.dto.StudentGroupsDto;
import ru.otus.thesis.service.StudentService;

@Slf4j
@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/api/student/{id}/groups")
    public ResponseEntity<StudentGroupsDto> getGroups(
            @PathVariable("id") long studentId
    ) {
        return ResponseEntity.ok()
                .body(studentService.getGroups(studentId));
    }

    @PostMapping("/api/student/{id}/group/{group_id}")
    public ResponseEntity<ResultDto> joinGroup(
            @PathVariable("id") long studentId,
            @PathVariable("group_id") long groupId
    ) {
        studentService.joinGroup(studentId, groupId);
        return ResponseEntity.ok()
                .body(ResultDto.OK);
    }

    @DeleteMapping("/api/student/{id}/group/{group_id}")
    public ResponseEntity<ResultDto> leaveGroup(
            @PathVariable("id") long studentId,
            @PathVariable("group_id") long groupId
    ) {
        studentService.leaveGroup(studentId, groupId);
        return ResponseEntity.ok()
                .body(ResultDto.OK);
    }
}
