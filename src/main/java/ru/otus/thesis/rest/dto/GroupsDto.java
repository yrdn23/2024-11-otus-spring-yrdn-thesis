package ru.otus.thesis.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.otus.thesis.model.Group;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GroupsDto {

    @JsonProperty("groups")
    private List<GroupDto> groups;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class GroupDto {

        @JsonProperty("id")
        private long id;

        @JsonProperty("code")
        private String code;

        @JsonProperty("name")
        private String name;

        @JsonProperty("start_date")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate startDate;

        @JsonProperty("end_date")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate endDate;

        @JsonProperty("course_id")
        private long courseId;

        @JsonProperty("course_title")
        private String courseTitle;

        @JsonProperty("course_description")
        private String courseDescription;

        @JsonProperty("students_count")
        private int studentsCount;
    }

    public static GroupDto from(Group group) {
        return new GroupDto()
                .setId(group.getId())
                .setCode(group.getCode())
                .setName(group.getName())
                .setStartDate(group.getStartDate())
                .setEndDate(group.getEndDate())
                .setCourseId(group.getCourse().getId())
                .setCourseTitle(group.getCourse().getTitle())
                .setCourseDescription(group.getCourse().getDescription())
                .setStudentsCount(group.getStudents().size());
    }
}
