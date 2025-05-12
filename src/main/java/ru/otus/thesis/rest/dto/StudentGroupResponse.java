package ru.otus.thesis.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.otus.thesis.model.Group;
import ru.otus.thesis.model.Lesson;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StudentGroupResponse {

    @JsonProperty("id")
    private long id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonProperty("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @JsonProperty("course_id")
    private long courseId;

    @JsonProperty("course_title")
    private String courseTitle;

    @JsonProperty("course_description")
    private String courseDescription;

    @JsonProperty("lessons")
    private List<LessonDto> lessons;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class LessonDto {

        @JsonProperty("id")
        private long id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("description")
        private String description;

        @JsonProperty("teacher_id")
        private long teacherId;

        @JsonProperty("teacher_first_name")
        private String teacherFirstName;

        @JsonProperty("teacher_last_name")
        private String teacherLastName;
    }

    public static StudentGroupResponse from(Group group) {
        return new StudentGroupResponse()
                .setId(group.getId())
                .setCode(group.getCode())
                .setName(group.getName())
                .setStartDate(group.getStartDate())
                .setEndDate(group.getEndDate())
                .setCourseId(group.getCourse().getId())
                .setCourseTitle(group.getCourse().getTitle())
                .setCourseDescription(group.getCourse().getDescription());
    }

    public static LessonDto from(Lesson lesson) {
        return new LessonDto()
                .setId(lesson.getId())
                .setTitle(lesson.getTitle())
                .setDescription(lesson.getDescription())
                .setTeacherId(lesson.getTeacher().getId())
                .setTeacherFirstName(lesson.getTeacher().getFirstName())
                .setTeacherLastName(lesson.getTeacher().getLastName());
    }
}
