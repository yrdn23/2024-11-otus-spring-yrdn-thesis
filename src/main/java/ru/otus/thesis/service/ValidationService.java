package ru.otus.thesis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.thesis.exceptions.InconsistentRequestException;
import ru.otus.thesis.model.Group;
import ru.otus.thesis.model.Student;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationService {

    private static final String STUDENT_IS_NOT_IN_GROUP = "Student %d is not in group %d";

    public void validateStudentInGroup(Student student, Group group) {
        if (!student.getGroups().contains(group)) {
            throw new InconsistentRequestException(STUDENT_IS_NOT_IN_GROUP
                    .formatted(student.getId(), group.getId()));
        }
    }
}
