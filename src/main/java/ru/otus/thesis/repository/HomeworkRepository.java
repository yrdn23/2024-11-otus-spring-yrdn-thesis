package ru.otus.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.thesis.model.Homework;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}
