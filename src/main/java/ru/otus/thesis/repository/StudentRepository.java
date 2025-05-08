package ru.otus.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.thesis.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
