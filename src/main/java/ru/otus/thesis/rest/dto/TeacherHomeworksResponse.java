package ru.otus.thesis.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.otus.thesis.model.Group;
import ru.otus.thesis.model.Homework;
import ru.otus.thesis.model.Teacher;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TeacherHomeworksResponse {

    @JsonProperty("teacher_id")
    private long teacherId;

    @JsonProperty("teacher_first_name")
    private String teacherFirstName;

    @JsonProperty("teacher_last_name")
    private String teacherLastName;

    @JsonProperty("group_id")
    private long groupId;

    @JsonProperty("group_code")
    private String groupCode;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("course_id")
    private long courseId;

    @JsonProperty("course_title")
    private String courseTitle;

    @JsonProperty("homeworks")
    private List<HomeworkDto> homeworks;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class HomeworkDto {

        @JsonProperty("id")
        private long id;

        @JsonProperty("submit_date")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDateTime submitDate;

        @JsonProperty("comment")
        private String comment;

        @JsonProperty("score")
        private int score;

        @JsonProperty("status")
        private String status;

        @JsonProperty("lesson_title")
        private String lessonTitle;

        @JsonProperty("lesson_deadline_date")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDateTime lessonDeadlineDate;

        @JsonProperty("teacher_id")
        private long teacherId;

        @JsonProperty("teacher_first_name")
        private String teacherFirstName;

        @JsonProperty("teacher_last_name")
        private String teacherLastName;
    }

    public static TeacherHomeworksResponse from(Teacher teacher, Group group) {
        return new TeacherHomeworksResponse()
                .setTeacherId(teacher.getId())
                .setTeacherFirstName(teacher.getFirstName())
                .setTeacherLastName(teacher.getLastName())
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
                .setStatus(homework.getStatus().name())
                .setLessonTitle(homework.getLesson().getTitle())
                .setLessonDeadlineDate(homework.getLesson().getDeadlineDate())
                .setTeacherId(homework.getLesson().getTeacher().getId())
                .setTeacherFirstName(homework.getLesson().getTeacher().getFirstName())
                .setTeacherLastName(homework.getLesson().getTeacher().getLastName());
    }
}
