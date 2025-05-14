package ru.otus.thesis.service;

import ru.otus.thesis.enums.MessageDirection;
import ru.otus.thesis.rest.dto.ResultResponse;
import ru.otus.thesis.rest.dto.MessageSendRequest;
import ru.otus.thesis.rest.dto.MessagesRequest;
import ru.otus.thesis.rest.dto.MessagesResponse;

public interface MessageService {

    MessagesResponse getMessages(MessagesRequest request);

    ResultResponse sendMessage(MessageSendRequest request, MessageDirection direction);
}
