package ru.otus.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.thesis.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
