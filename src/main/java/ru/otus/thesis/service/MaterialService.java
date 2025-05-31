package ru.otus.thesis.service;

import ru.otus.thesis.model.Material;

import java.util.List;

public interface MaterialService {

    List<Material> getAllMaterialsByLessonId(Long lessonId);

    Material addMaterial(Long lessonId, Material material);

    Material updateMaterial(Long lessonId, Long materialId, Material updatedMaterial);

    void deleteMaterial(Long lessonId, Long materialId);
}