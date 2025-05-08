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
import ru.otus.thesis.enums.HomeworkStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "homeworks")
@Getter
@Setter
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "answer")
    private String answer;

    @Column(name = "submit_date")
    private LocalDateTime submitDate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "score")
    private int score;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private HomeworkStatus status;
}
