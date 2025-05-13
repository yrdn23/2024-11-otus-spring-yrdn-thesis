package ru.otus.thesis.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class StudentMessageSendRequest {

    @NotNull(message = "Field 'student_id' should have value")
    @JsonProperty("student_id")
    private Long studentId;

    @NotNull(message = "Field 'teacher_id' should have value")
    @JsonProperty("teacher_id")
    private Long teacherId;

    @NotBlank(message = "Field 'text' should have value")
    @JsonProperty("text")
    private String text;
}
