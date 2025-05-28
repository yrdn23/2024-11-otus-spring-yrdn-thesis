package ru.otus.thesis.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.thesis.controller.dto.AverageDurationReportResponse;
import ru.otus.thesis.controller.dto.StudentProgressReportResponse;

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
                       round(avg(w.score), 4) average_score,
                       count(1) count
                from homeworks w
                join lessons l on w.lesson_id = l.id
                join groups g on l.group_id = g.id
                join courses c on g.course_id = c.id
                where w.submit_date is not null
                group by g.course_id, c.title, w.lesson_id, l.title
                order by 5 desc, g.course_id, c.title, w.lesson_id, l.title
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> AverageDurationReportResponse.from(rs));
    }

    public List<StudentProgressReportResponse.Row> reportStudentProgress() {
        String sql = """
                select s.user_id, u.first_name, u.last_name,
                       sum(case when w.submit_date <= l.deadline_date and w.status = 'ACCEPTED' then 1 else 0 end)
                           accepted_in_time_count,
                       sum(case when w.status = 'ACCEPTED' then 1 else 0 end) accepted_count,
                       sum(case when w.status = 'ACCEPTED' then 0 else 1 end) not_accepted_count,
                       count(1) total
                from students s
                join users u on s.user_id = u.id
                join homeworks w on s.user_id = w.student_id
                join lessons l on w.lesson_id = l.id
                group by s.user_id, u.first_name, u.last_name
                order by u.first_name, u.last_name, s.user_id
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> StudentProgressReportResponse.from(rs));
    }
}
