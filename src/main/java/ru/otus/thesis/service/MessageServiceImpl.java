package ru.otus.thesis.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.thesis.enums.MessageDirection;
import ru.otus.thesis.exceptions.EntityNotFoundException;
import ru.otus.thesis.model.Message;
import ru.otus.thesis.model.Student;
import ru.otus.thesis.model.Teacher;
import ru.otus.thesis.repository.MessageRepository;
import ru.otus.thesis.repository.StudentRepository;
import ru.otus.thesis.repository.TeacherRepository;
import ru.otus.thesis.controller.dto.ResultResponse;
import ru.otus.thesis.controller.dto.MessageSendRequest;
import ru.otus.thesis.controller.dto.MessagesRequest;
import ru.otus.thesis.controller.dto.MessagesResponse;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private static final String STUDENT_NOT_FOUND = "Student not found with id: %d";

    private static final String TEACHER_NOT_FOUND = "Teacher not found with id: %d";

    private final MessageRepository messageRepository;

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    @Override
    @Transactional(readOnly = true)
    public MessagesResponse getMessages(MessagesRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException(STUDENT_NOT_FOUND.formatted(request.getStudentId())));
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException(TEACHER_NOT_FOUND.formatted(request.getTeacherId())));

        List<MessagesResponse.MessageDto> messages = student.getMessages().stream()
                .filter(message -> message.getTeacher().getId() == teacher.getId())
                .map(MessagesResponse::from)
                .toList();

        return MessagesResponse.from(student, teacher)
                .setMessages(messages);

    }

    @Override
    @Transactional
    public ResultResponse sendMessage(MessageSendRequest request, MessageDirection direction) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException(STUDENT_NOT_FOUND.formatted(request.getStudentId())));
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException(TEACHER_NOT_FOUND.formatted(request.getTeacherId())));

        Message message = new Message()
                .setStudent(student)
                .setTeacher(teacher)
                .setMessageDirection(direction)
                .setText(request.getText())
                .setMessageTime(LocalDateTime.now());

        messageRepository.save(message);

        return ResultResponse.OK;
    }
}
