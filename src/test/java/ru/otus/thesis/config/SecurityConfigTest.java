package ru.otus.thesis.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.thesis.rest.StudentController;
import ru.otus.thesis.rest.TeacherController;
import ru.otus.thesis.service.MessageService;
import ru.otus.thesis.service.StudentService;
import ru.otus.thesis.service.TeacherService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({StudentController.class, TeacherController.class})
@Import(SecurityConfig.class)
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private MessageService messageService;

    private static final String REQUEST_BODY = "{\"student_id\": 2, \"teacher_id\": 7, \"text\": \"text1\"}";

    @Test
    void testStudentEndpointByTeacher() throws Exception {

        mockMvc.perform(post("/api/student/message/send")
                        .with(user("user5").password("user5").roles("TEACHER")))
                .andExpect(status().isForbidden());
    }

    @Test
    void testStudentEndpointByStudent() throws Exception {

        mockMvc.perform(post("/api/student/message/send")
                        .with(user("user2").password("user2").roles("STUDENT"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(REQUEST_BODY))
                .andExpect(status().isOk());
    }

    @Test
    void testTeacherEndpointByStudent() throws Exception {

        mockMvc.perform(post("/api/teacher/message/send")
                        .with(user("user2").password("user2").roles("STUDENT")))
                .andExpect(status().isForbidden());
    }

    @Test
    void testTeacherEndpointByTeacher() throws Exception {

        mockMvc.perform(post("/api/teacher/message/send")
                        .with(user("user5").password("user5").roles("TEACHER"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(REQUEST_BODY))
                .andExpect(status().isOk());
    }
}
