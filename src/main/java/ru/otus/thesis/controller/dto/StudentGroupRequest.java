package ru.otus.thesis.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StudentGroupRequest {

    @JsonProperty("student_id")
    @Schema(description = "Идентификатор студента")
    @NotNull(message = "Field 'student_id' should have value")
    private Long studentId;

    @JsonProperty("group_id")
    @Schema(description = "Идентификатор группы")
    @NotNull(message = "Field 'group_id' should have value")
    private Long groupId;
}
