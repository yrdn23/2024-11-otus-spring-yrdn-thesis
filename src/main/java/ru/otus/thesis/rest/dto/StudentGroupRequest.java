package ru.otus.thesis.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private long studentId;

    @JsonProperty("group_id")
    private long groupId;
}
