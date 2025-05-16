package ru.otus.thesis.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.thesis.rest.dto.AverageDurationReportResponse;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<AverageDurationReportResponse.Row> reportAverageDuration() {
        String sql = """
                select g.course_id, c.title course_title, w.lesson_id, l.title lesson_title,
                       round(avg((extract(epoch from w.submit_date) - extract(epoch from l.start_date)) / 
                                 86400.0), 4) average_duration,
                       count(1) count
                from homeworks w
                join lessons l on w.lesson_id = l.id
                join groups g on l.group_id = g.id
                join courses c on g.course_id = c.id
                where w.submit_date is not null
                group by g.course_id, c.title, w.lesson_id, l.title
                order by 5 desc, g.course_id, c.title, w.lesson_id, l.title
                """;

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> AverageDurationReportResponse.from(rs));
    }
}
