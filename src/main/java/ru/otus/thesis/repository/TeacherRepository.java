package ru.otus.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.thesis.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
