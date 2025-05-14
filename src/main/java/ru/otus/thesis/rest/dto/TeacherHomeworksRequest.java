package ru.otus.thesis.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TeacherHomeworksRequest {

    @NotNull(message = "Field 'teacher_id' should have value")
    @JsonProperty("teacher_id")
    private Long teacherId;

    @NotNull(message = "Field 'group_id' should have value")
    @JsonProperty("group_id")
    private Long groupId;
}
