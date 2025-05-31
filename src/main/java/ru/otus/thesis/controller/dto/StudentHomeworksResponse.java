package ru.otus.thesis.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.otus.thesis.enums.HomeworkStatus;
import ru.otus.thesis.model.Group;
import ru.otus.thesis.model.Homework;
import ru.otus.thesis.model.Student;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StudentHomeworksResponse {

    @JsonProperty("student_id")
    @Schema(description = "Идентификатор студента")
    private long studentId;

    @JsonProperty("student_first_name")
    @Schema(description = "Имя студента")
    private String studentFirstName;

    @JsonProperty("student_last_name")
    @Schema(description = "Фамилия студента")
    private String studentLastName;

    @JsonProperty("group_id")
    @Schema(description = "Идентификатор группы")
    private long groupId;

    @JsonProperty("group_code")
    @Schema(description = "Код группы")
    private String groupCode;

    @JsonProperty("group_name")
    @Schema(description = "Название группы")
    private String groupName;

    @JsonProperty("course_id")
    @Schema(description = "Идентификатор курса")
    private long courseId;

    @JsonProperty("course_title")
    @Schema(description = "Название курса")
    private String courseTitle;

    @JsonProperty("homeworks")
    @Schema(description = "Список домашних заданий")
    private List<HomeworkDto> homeworks;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class HomeworkDto {

        @JsonProperty("id")
        @Schema(description = "Идентификатор домашнего задания")
        private Long id;

        @JsonProperty("submit_date")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @Schema(description = "Дата сдачи домашнего задания")
        private LocalDateTime submitDate;

        @JsonProperty("comment")
        @Schema(description = "Комментарий преподавателя")
        private String comment;

        @JsonProperty("score")
        @Schema(description = "Оценка")
        private Integer score;

        @JsonProperty("status")
        @Enumerated(EnumType.STRING)
        @Schema(description = "Статус")
        private HomeworkStatus status;

        @JsonProperty("lesson_id")
        @Schema(description = "Идентификатор занятия")
        private Long lessonId;

        @JsonProperty("lesson_title")
        @Schema(description = "Название занятия")
        private String lessonTitle;

        @JsonProperty("lesson_deadline_date")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @Schema(description = "Дата окончания приема домашнего задания")
        private LocalDateTime lessonDeadlineDate;

        @JsonProperty("teacher_id")
        @Schema(description = "Идентификатор преподавателя, проверяющего домашнее задания")
        private Long teacherId;

        @JsonProperty("teacher_first_name")
        @Schema(description = "Имя преподавателя, проверяющего домашнее задания")
        private String teacherFirstName;

        @JsonProperty("teacher_last_name")
        @Schema(description = "Фамилия преподавателя, проверяющего домашнее задания")
        private String teacherLastName;
    }

    public static StudentHomeworksResponse from(Student student, Group group) {
        return new StudentHomeworksResponse()
                .setStudentId(student.getId())
                .setStudentFirstName(student.getFirstName())
                .setStudentLastName(student.getLastName())
                .setGroupId(group.getId())
                .setGroupCode(group.getCode())
                .setGroupName(group.getName())
                .setCourseId(group.getCourse().getId())
                .setCourseTitle(group.getCourse().getTitle());
    }

    public static HomeworkDto from(Homework homework) {
        return new HomeworkDto()
                .setId(homework.getId())
                .setSubmitDate(homework.getSubmitDate())
                .setComment(homework.getComment())
                .setScore(homework.getScore())
                .setStatus(homework.getStatus())
                .setLessonId(homework.getLesson().getId())
                .setLessonTitle(homework.getLesson().getTitle())
                .setLessonDeadlineDate(homework.getLesson().getDeadlineDate())
                .setTeacherId(homework.getLesson().getTeacher().getId())
                .setTeacherFirstName(homework.getLesson().getTeacher().getFirstName())
                .setTeacherLastName(homework.getLesson().getTeacher().getLastName());
    }
}
