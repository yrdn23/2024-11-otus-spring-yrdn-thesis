package ru.otus.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.thesis.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
