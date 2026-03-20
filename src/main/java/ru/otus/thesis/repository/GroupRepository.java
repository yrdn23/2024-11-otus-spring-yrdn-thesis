package ru.otus.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.thesis.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
