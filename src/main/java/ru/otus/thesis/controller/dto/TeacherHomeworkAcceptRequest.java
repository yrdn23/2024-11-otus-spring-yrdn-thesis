package ru.otus.thesis.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.otus.thesis.enums.HomeworkStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TeacherHomeworkAcceptRequest {

    @JsonProperty("teacher_id")
    @NotNull(message = "Field 'teacher_id' should have value")
    @Schema(description = "Идентификатор преподавателя")
    private Long teacherId;

    @JsonProperty("homework_id")
    @NotNull(message = "Field 'homework_id' should have value")
    @Schema(description = "Идентификатор домашнего задания")
    private Long homeworkId;

    @JsonProperty("score")
    @NotNull(message = "Field 'score' sohuld have value")
    @Min(value = 0, message = "Field 'score' should be equal or more than {value}")
    @Max(value = 10, message = "Field 'score' should be equal or less than {value}")
    @Schema(description = "Оценка домашнего задания")
    private Integer score;

    @JsonProperty("comment")
    @Schema(description = "Комментарий преподавателя")
    private String comment;

    @JsonProperty("status")
    @Schema(description = "Статус домашнего задания")
    private HomeworkStatus status;
}
