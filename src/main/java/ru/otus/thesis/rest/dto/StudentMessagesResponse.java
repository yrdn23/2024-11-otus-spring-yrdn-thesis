package ru.otus.thesis.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.otus.thesis.model.Message;
import ru.otus.thesis.model.Student;
import ru.otus.thesis.model.Teacher;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StudentMessagesResponse {

    @JsonProperty("student_id")
    private long studentId;

    @JsonProperty("student_first_name")
    private String studentFirstName;

    @JsonProperty("student_second_name")
    private String studentSecondName;

    @JsonProperty("teacher_id")
    private long teacherId;

    @JsonProperty("teacher_first_name")
    private String teacherFirstName;

    @JsonProperty("teacher_second_name")
    private String teacherSecondName;

    @JsonProperty("messages")
    private List<MessageDto> messages;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class MessageDto {

        @JsonProperty("id")
        private long id;

        @JsonProperty("text")
        private String text;

        @JsonProperty("message_time")
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
        private LocalDateTime messageTime;
    }

    public static StudentMessagesResponse from(Student student, Teacher teacher) {
        return new StudentMessagesResponse()
                .setStudentId(student.getId())
                .setStudentFirstName(student.getFirstName())
                .setStudentSecondName(student.getLastName())
                .setTeacherId(teacher.getId())
                .setTeacherFirstName(teacher.getFirstName())
                .setTeacherSecondName(teacher.getLastName());
    }

    public static MessageDto from(Message message) {
        return new MessageDto()
                .setId(message.getId())
                .setText(message.getText())
                .setMessageTime(message.getMessageTime());
    }
}
