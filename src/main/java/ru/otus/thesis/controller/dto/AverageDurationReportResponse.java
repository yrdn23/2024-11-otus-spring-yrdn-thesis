package ru.otus.thesis.controller.dto;


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
public class AverageDurationReportResponse {

    @JsonProperty("rows")
    @Schema(description = "Строки отчета")
    private List<Row> rows;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class Row {

        @JsonProperty("course_id")
        @Schema(description = "Идентификатор курса")
        private Long courseId;

        @JsonProperty("course_title")
        @Schema(description = "Название курса")
        private String courseTitle;

        @JsonProperty("lesson_id")
        @Schema(description = "Идентификатор занятия")
        private Long lessonId;

        @JsonProperty("lesson_title")
        @Schema(description = "Название занятия")
        private String lessonTitle;

        @JsonProperty("average_duration")
        @Schema(description = "Среднее время сдачи домашнего задания в днях")
        private Double averageDuration;

        @JsonProperty("average_score")
        @Schema(description = "Средняя оценка за домашнее задание")
        private Double averageScore;

        @JsonProperty("count")
        @Schema(description = "Количество проанализированных домашних заданий")
        private Integer count;
    }

    public static Row from (ResultSet resultSet) throws SQLException {
        return new Row()
                .setCourseId(resultSet.getLong("course_id"))
                .setCourseTitle(resultSet.getString("course_title"))
                .setLessonId(resultSet.getLong("lesson_id"))
                .setLessonTitle(resultSet.getString("lesson_title"))
                .setAverageDuration(resultSet.getDouble("average_duration"))
                .setAverageScore(resultSet.getDouble("average_score"))
                .setCount(resultSet.getInt("count"));
    }
}
