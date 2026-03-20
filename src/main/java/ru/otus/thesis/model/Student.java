package ru.otus.thesis.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
public class Student extends User {

    @Column(name = "enrollment_date")
    private LocalDateTime enrollmentDate;

    @ManyToMany(mappedBy = "students")
    private List<Group> groups;

    @OneToMany(mappedBy = "student")
    private List<Homework> homeworks;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;
}
