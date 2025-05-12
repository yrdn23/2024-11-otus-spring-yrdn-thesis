package ru.otus.thesis.service;

import ru.otus.thesis.model.Group;
import ru.otus.thesis.model.Student;

public interface ValidationService {

    void validateStudentInGroup(Student student, Group group);

}
