package ru.otus.thesis.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import ru.otus.thesis.config.TestSecurityConfig;
import ru.otus.thesis.model.Lesson;
import ru.otus.thesis.model.Material;
import ru.otus.thesis.service.MaterialService;

import java.util.Base64;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MaterialController.class)
@Import(TestSecurityConfig.class)
class MaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaterialService materialService;

    @Autowired
    private ObjectMapper objectMapper;

    private RequestPostProcessor addBasicAuth() {
        return request -> {
            String auth = "testuser:testpass";
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
            String authHeader = "Basic " + new String(encodedAuth);
            request.addHeader("Authorization", authHeader);
            return request;
        };
    }

    private final Material material = new Material()
            .setId(1L)
            .setTitle("Test Material")
            .setDescription("Description")
            .setUrl("http://example.com")
            .setLesson(new Lesson().setId(1L));

    @Test
    void getAllMaterials_shouldReturnMaterials() throws Exception {
        when(materialService.getAllMaterialsByLessonId(10L))
                .thenReturn(List.of(material));

        mockMvc.perform(get("/api/lessons/10/materials")
                        .with(addBasicAuth()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Test Material"))
                .andExpect(jsonPath("$[0].description").value("Description"));

        verify(materialService).getAllMaterialsByLessonId(10L);
    }

    @Test
    void createMaterial_shouldReturnCreated() throws Exception {
        when(materialService.addMaterial(anyLong(), any(Material.class)))
                .thenReturn(material);

        mockMvc.perform(post("/api/lessons/10/materials")
                        .with(addBasicAuth())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(material)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Material"));

        verify(materialService).addMaterial(eq(10L), any(Material.class));
    }

    @Test
    void updateMaterial_shouldReturnUpdatedMaterial() throws Exception {
        when(materialService.updateMaterial(eq(10L), eq(1L), any(Material.class)))
                .thenReturn(material);

        mockMvc.perform(put("/api/lessons/10/materials/1")
                        .with(addBasicAuth())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(material)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Material"));

        verify(materialService).updateMaterial(eq(10L), eq(1L), any(Material.class));
    }

    @Test
    void deleteMaterial_shouldReturnNoContent() throws Exception {
        doNothing().when(materialService).deleteMaterial(10L, 1L);

        mockMvc.perform(delete("/api/lessons/10/materials/1")
                        .with(addBasicAuth()))
                .andExpect(status().isOk());

        verify(materialService).deleteMaterial(10L, 1L);
    }
}