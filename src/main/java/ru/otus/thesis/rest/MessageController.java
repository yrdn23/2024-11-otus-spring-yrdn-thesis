package ru.otus.thesis.rest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.thesis.rest.dto.MessagesRequest;
import ru.otus.thesis.rest.dto.MessagesResponse;
import ru.otus.thesis.service.MessageService;

@Slf4j
@RestController
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @Operation(summary = "MVP: Возможность переписываться со студентом/преподавателем")
    @PostMapping("/api/messages")
    public ResponseEntity<MessagesResponse> getMessages(
            @RequestBody @Valid MessagesRequest request
    ) {
        return ResponseEntity.ok()
                .body(messageService.getMessages(request));
    }
}
