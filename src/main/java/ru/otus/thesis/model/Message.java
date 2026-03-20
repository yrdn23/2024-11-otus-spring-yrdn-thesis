package ru.otus.thesis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.otus.thesis.enums.MessageDirection;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@Accessors(chain = true)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "message_direction")
    @Enumerated(EnumType.STRING)
    private MessageDirection messageDirection;

    @Column(name = "text")
    private String text;

    @Column(name = "message_time")
    private LocalDateTime messageTime;
}
