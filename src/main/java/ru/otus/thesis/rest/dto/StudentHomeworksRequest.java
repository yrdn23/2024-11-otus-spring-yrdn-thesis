package ru.otus.thesis.rest.dto;

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
public class StudentHomeworksRequest {

    @JsonProperty("student_id")
    @NotNull(message = "Field 'student_id' should have value")
    @Schema(description = "Идентификатор студента")
    private Long studentId;

    @JsonProperty("group_id")
    @NotNull(message = "Field 'group_id' should have value")
    @Schema(description = "Идентификатор группы")
    private Long groupId;
}
