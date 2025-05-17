package ru.otus.thesis.rest.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StudentProgressReportResponse {

    @JsonProperty("rows")
    @Schema(description = "Строки отчета")
    private List<Row> rows;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class Row {

        @JsonProperty("student_id")
        @Schema(description = "Идентификатор студента")
        private String studentId;

        @JsonProperty("student_first_name")
        @Schema(description = "Имя студента")
        private String studentFirstName;

        @JsonProperty("student_last_name")
        @Schema(description = "Фамилия студента")
        private String studentLastName;

        @JsonProperty("accepted_in_time_count")
        @Schema(description = "Количество принятых во время домашних заданий")
        private Integer acceptedInTimeCount;

        @JsonProperty("accepted_count")
        @Schema(description = "Количество принятых домашних заданий")
        private Integer acceptedCount;

        @JsonProperty("not_accepted_count")
        @Schema(description = "Количество не сданных домашних заданий")
        private Integer notAcceptedCount;

        @JsonProperty("total")
        @Schema(description = "Общее количеество выполненных домашних заданий")
        private Integer total;
    }

    public static Row from (ResultSet resultSet) throws SQLException {
        return new Row()
                .setStudentId(resultSet.getString("user_id"))
                .setStudentFirstName(resultSet.getString("first_name"))
                .setStudentLastName(resultSet.getString("last_name"))
                .setAcceptedInTimeCount(resultSet.getInt("accepted_in_time_count"))
                .setAcceptedCount(resultSet.getInt("accepted_count"))
                .setNotAcceptedCount(resultSet.getInt("not_accepted_count"))
                .setTotal(resultSet.getInt("total"));
    }
}
