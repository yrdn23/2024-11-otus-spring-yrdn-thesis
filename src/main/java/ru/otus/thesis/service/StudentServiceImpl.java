package ru.otus.thesis.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.thesis.exceptions.EntityNotFoundException;
import ru.otus.thesis.model.Group;
import ru.otus.thesis.model.Student;
import ru.otus.thesis.repository.GroupRepository;
import ru.otus.thesis.repository.StudentRepository;
import ru.otus.thesis.rest.dto.StudentGroupsDto;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;

    @Override
    public StudentGroupsDto getGroups(long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        List<StudentGroupsDto.GroupDto> groups = student.getGroups().stream()
                .map(StudentGroupsDto::from)
                .toList();

        return StudentGroupsDto.from(student)
                .setGroups(groups);
    }

    @Override
    @Transactional
    public void joinGroup(long studentId, long groupId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + groupId));

        if (!group.getStudents().contains(student)) {
            group.getStudents().add(student);
        }

        groupRepository.save(group);
    }

    @Override
    public void leaveGroup(long studentId, long groupId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + groupId));

        group.getStudents().remove(student);

        groupRepository.save(group);
    }
}
