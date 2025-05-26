package ru.otus.thesis.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.thesis.exceptions.EntityNotFoundException;
import ru.otus.thesis.model.Lesson;
import ru.otus.thesis.model.Material;
import ru.otus.thesis.repository.LessonRepository;
import ru.otus.thesis.repository.MaterialRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private static final String LESSON_NOT_FOUND = "Lesson not found with id: %d";

    private static final String MATERIAL_NOT_FOUND = "Material not found with id: %d";

    private final LessonRepository lessonRepository;

    private final MaterialRepository materialRepository;

    @Override
    public List<Material> getAllMaterialsByLessonId(Long lessonId) {
        return materialRepository.findByLessonId(lessonId);
    }

    @Override
    public Material addMaterial(Long lessonId, Material material) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException(LESSON_NOT_FOUND.formatted(lessonId)));
        material.setLesson(lesson);
        return materialRepository.save(material);
    }

    @Override
    public Material updateMaterial(Long lessonId, Long materialId, Material updatedMaterial) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException(LESSON_NOT_FOUND.formatted(lessonId)));

        return materialRepository.findById(materialId)
                .filter(material -> material.getLesson().equals(lesson))
                .map(material -> {
                    material.setTitle(updatedMaterial.getTitle());
                    material.setDescription(updatedMaterial.getDescription());
                    material.setUrl(updatedMaterial.getUrl());
                    return materialRepository.save(material);
                })
                .orElseThrow(() -> new EntityNotFoundException(MATERIAL_NOT_FOUND.formatted(materialId)));
    }

    @Override
    public void deleteMaterial(Long lessonId, Long materialId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException(LESSON_NOT_FOUND.formatted(lessonId)));

        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new EntityNotFoundException(MATERIAL_NOT_FOUND.formatted(materialId)));

        if (!material.getLesson().equals(lesson)) {
            throw new EntityNotFoundException(MATERIAL_NOT_FOUND.formatted(materialId));
        }

        materialRepository.deleteById(materialId);
    }
}
