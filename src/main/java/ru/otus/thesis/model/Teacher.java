package ru.otus.thesis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
public class Teacher extends User {
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "specialization")
    private String specialization;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    @OneToMany(mappedBy = "teacher")
    private List<Lesson> lessons;
}
