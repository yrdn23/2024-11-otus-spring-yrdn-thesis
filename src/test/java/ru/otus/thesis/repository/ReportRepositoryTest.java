package ru.otus.thesis.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReportRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ReportRepository reportRepository;

    @Test
    void reportAverageDurationTest() {
        reportRepository = new ReportRepository(jdbcTemplate);

        var actualResult = reportRepository.reportAverageDuration();

        assertThat(actualResult)
                .hasSize(12);
    }

    @Test
    void reportStudentProgressTest() {
        reportRepository = new ReportRepository(jdbcTemplate);

        var actualResult = reportRepository.reportStudentProgress();

        assertThat(actualResult)
                .hasSize(3);
    }
}