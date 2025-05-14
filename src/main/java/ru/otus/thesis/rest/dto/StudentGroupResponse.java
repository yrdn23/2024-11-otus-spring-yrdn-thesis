package ru.otus.thesis.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Идентификатор группы")
    private long id;

    @JsonProperty("code")
    @Schema(description = "Код группы")
    private String code;

    @JsonProperty("name")
    @Schema(description = "Название группы")
    private String name;

    @JsonProperty("start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Дата начала группы")
    private LocalDate startDate;

    @JsonProperty("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Дата окончания группы")
    private LocalDate endDate;

    @JsonProperty("course_id")
    @Schema(description = "Идентификатор курса")
    private long courseId;

    @JsonProperty("course_title")
    @Schema(description = "Название курса")
    private String courseTitle;

    @JsonProperty("course_description")
    @Schema(description = "Описание курса")
    private String courseDescription;

    @JsonProperty("lessons")
    @Schema(description = "Список занятий")
    private List<LessonDto> lessons;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class LessonDto {

        @JsonProperty("id")
        @Schema(description = "Идентификатор занятия")
        private long id;

        @JsonProperty("title")
        @Schema(description = "Название занятия")
        private String title;

        @JsonProperty("description")
        @Schema(description = "Описание занятия")
        private String description;

        @JsonProperty("teacher_id")
        @Schema(description = "Идентификатор преподавателя")
        private long teacherId;

        @JsonProperty("teacher_first_name")
        @Schema(description = "Имя преподавателя")
        private String teacherFirstName;

        @JsonProperty("teacher_last_name")
        @Schema(description = "Фамилия преподавателя")
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
