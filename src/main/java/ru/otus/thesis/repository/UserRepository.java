package ru.otus.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.thesis.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
