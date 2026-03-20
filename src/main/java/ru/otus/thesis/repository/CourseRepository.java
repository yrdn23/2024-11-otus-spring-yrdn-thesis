package ru.otus.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.thesis.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
