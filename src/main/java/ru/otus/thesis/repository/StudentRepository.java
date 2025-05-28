package ru.otus.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.thesis.model.Student;

import java.time.LocalDateTime;

public interface StudentRepository extends JpaRepository<Student, Long> {
    long countByRegisterDateAfter(LocalDateTime date);
}
