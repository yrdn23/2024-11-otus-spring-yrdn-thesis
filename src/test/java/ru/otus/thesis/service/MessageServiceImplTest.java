package ru.otus.thesis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.thesis.enums.MessageDirection;
import ru.otus.thesis.exceptions.EntityNotFoundException;
import ru.otus.thesis.controller.dto.MessageSendRequest;
import ru.otus.thesis.controller.dto.MessagesRequest;
import ru.otus.thesis.controller.dto.MessagesResponse;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MessageServiceImplTest {

    @Autowired
    private MessageService messageService;

    @Test
    void getMessagesStudentNotFound() {
        MessagesRequest request = new MessagesRequest()
                .setStudentId(-1000L)
                .setTeacherId(-1000L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> messageService.getMessages(request));

        assertEquals("Student not found with id: -1000", exception.getMessage());
    }

    @Test
    void getMessagesTeacherNotFound() {
        MessagesRequest request = new MessagesRequest()
                .setStudentId(2L)
                .setTeacherId(-1000L);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> messageService.getMessages(request));

        assertEquals("Teacher not found with id: -1000", exception.getMessage());
    }

    @Test
    @Transactional(readOnly = true)
    void getMessagesSuccess() {
        MessagesRequest request = new MessagesRequest()
                .setStudentId(2L)
                .setTeacherId(5L);

        MessagesResponse actualResult = messageService.getMessages(request);

        assertEquals(2L, actualResult.getStudentId());
        assertEquals(5L, actualResult.getTeacherId());
        assertEquals(4, actualResult.getMessages().size());
        assertTrue(actualResult.getMessages().stream()
                .map(MessagesResponse.MessageDto::getId)
                .collect(Collectors.toSet())
                .containsAll(Set.of(1001L, 1003L, 1005L, 1007L)));
    }

    @Test
    void sendMessageStudentNotFound() {
        MessageSendRequest request = new MessageSendRequest()
                .setStudentId(-1000L)
                .setTeacherId(-1000L)
                .setText("text of message");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> messageService.sendMessage(request, MessageDirection.S2T));

        assertEquals("Student not found with id: -1000", exception.getMessage());
    }

    @Test
    void sendMessageTeacherNotFound() {
        MessageSendRequest request = new MessageSendRequest()
                .setStudentId(2L)
                .setTeacherId(-1000L)
                .setText("text of message");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> messageService.sendMessage(request, MessageDirection.S2T));

        assertEquals("Teacher not found with id: -1000", exception.getMessage());
    }

    @Test
    void sendMessageSuccess() {
        MessageSendRequest request = new MessageSendRequest()
                .setStudentId(2L)
                .setTeacherId(7L)
                .setText("text of message");

        messageService.sendMessage(request, MessageDirection.S2T);

        MessagesResponse response = messageService.getMessages(
                new MessagesRequest()
                        .setStudentId(2L)
                        .setTeacherId(7L));

        assertEquals(2, response.getMessages().size());
    }
}