package ru.otus.thesis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.thesis.exceptions.InconsistentRequestException;
import ru.otus.thesis.model.Group;
import ru.otus.thesis.model.Homework;
import ru.otus.thesis.model.Student;
import ru.otus.thesis.model.Teacher;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationService {

    private static final String STUDENT_IS_NOT_IN_GROUP = "Student %d is not in group %d";

    private static final String HOMEWORK_DOES_NOT_OF_TEACHER = "Homework %d does not of teacher %d";

    public void validateStudentInGroup(Student student, Group group) {
        if (!student.getGroups().contains(group)) {
            throw new InconsistentRequestException(STUDENT_IS_NOT_IN_GROUP
                    .formatted(student.getId(), group.getId()));
        }
    }

    public void validateHomeworkOfTeacher(Teacher teacher, Homework homework) {
        if (!homework.getLesson().getTeacher().equals(teacher)) {
            throw new InconsistentRequestException(HOMEWORK_DOES_NOT_OF_TEACHER
                    .formatted(homework.getId(), teacher.getId()));
        }
    }
}
