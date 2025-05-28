package ru.otus.thesis.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MessageSendRequest {

    @JsonProperty("student_id")
    @NotNull(message = "Field 'student_id' should have value")
    @Schema(description = "Идентификатор студента")
    private Long studentId;

    @JsonProperty("teacher_id")
    @NotNull(message = "Field 'teacher_id' should have value")
    @Schema(description = "Идентификатор преподавателя")
    private Long teacherId;

    @JsonProperty("text")
    @NotBlank(message = "Field 'text' should have value")
    @Schema(description = "Текст сообщения")
    private String text;
}
