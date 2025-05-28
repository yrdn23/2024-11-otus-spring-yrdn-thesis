package ru.otus.thesis.service;

import ru.otus.thesis.enums.MessageDirection;
import ru.otus.thesis.controller.dto.ResultResponse;
import ru.otus.thesis.controller.dto.MessageSendRequest;
import ru.otus.thesis.controller.dto.MessagesRequest;
import ru.otus.thesis.controller.dto.MessagesResponse;

public interface MessageService {

    MessagesResponse getMessages(MessagesRequest request);

    ResultResponse sendMessage(MessageSendRequest request, MessageDirection direction);
}
