package ru.otus.thesis.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.otus.thesis.model.Group;
import ru.otus.thesis.model.Student;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StudentGroupsDto {

    @JsonProperty("id")
    private long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("groups")
    private List<GroupDto> groups;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class GroupDto {

        @JsonProperty("id")
        private long id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("course_id")
        private long courseId;

        @JsonProperty("course_title")
        private String courseTitle;
    }

    public static StudentGroupsDto from(Student student) {
        return new StudentGroupsDto()
                .setId(student.getId())
                .setFirstName(student.getFirstName())
                .setLastName(student.getLastName());
    }

    public static GroupDto from(Group group) {
        return new GroupDto()
                .setId(group.getId())
                .setName(group.getName())
                .setCourseId(group.getCourse().getId())
                .setCourseTitle(group.getCourse().getTitle());
    }
}
