package ru.otus.thesis.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.thesis.rest.dto.GroupLessonsDto;
import ru.otus.thesis.rest.dto.GroupsDto;
import ru.otus.thesis.service.GroupService;

@Slf4j
@RestController
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/api/groups")
    public ResponseEntity<GroupsDto> getGroups() {
        return ResponseEntity.ok()
                .body(groupService.getGroups());
    }

    @PostMapping("/api/groups/{id}/lessons")
    public ResponseEntity<GroupLessonsDto> getLessons(
            @PathVariable("id") long groupId
    ) {
        return ResponseEntity.ok()
                .body(groupService.getLessons(groupId));
    }

}
