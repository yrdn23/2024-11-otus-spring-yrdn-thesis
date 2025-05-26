package ru.otus.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.thesis.model.Material;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findByLessonId(long lessonId);

    Integer countByLessonId(long lessonId);
}
