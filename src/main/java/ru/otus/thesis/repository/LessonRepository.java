package ru.otus.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.thesis.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
