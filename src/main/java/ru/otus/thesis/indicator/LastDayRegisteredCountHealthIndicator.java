package ru.otus.thesis.indicator;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.thesis.service.StudentService;

@Component
@AllArgsConstructor
public class LastDayRegisteredCountHealthIndicator implements HealthIndicator {

    private final StudentService studentService;

    @Override
    public Health health() {
        Long registeredStudentsCount = studentService.getRegisteredLastDay();
        if (registeredStudentsCount > 0) {
            return Health
                    .up()
                    .withDetail("registeredStudentsCount", registeredStudentsCount)
                    .build();
        }
        return Health.down().build();
    }
}
