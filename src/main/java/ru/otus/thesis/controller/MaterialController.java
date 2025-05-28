package ru.otus.thesis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.thesis.model.Material;
import ru.otus.thesis.service.MaterialService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping("/api/lessons/{lessonId}/materials")
    public ResponseEntity<List<Material>> getAllMaterialsByLessonId(@PathVariable Long lessonId) {
        return ResponseEntity.ok()
                .body(materialService.getAllMaterialsByLessonId(lessonId));
    }

    @PostMapping("/api/lessons/{lessonId}/materials")
    public ResponseEntity<Material> addMaterial(
            @PathVariable Long lessonId,
            @RequestBody Material material
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(materialService.addMaterial(lessonId, material));
    }

    @PutMapping("/api/lessons/{lessonId}/materials/{materialId}")
    public ResponseEntity<Material> updateMaterial(
            @PathVariable Long lessonId,
            @PathVariable Long materialId,
            @RequestBody Material updatedMaterial
    ) {
        return ResponseEntity.ok()
                .body(materialService.updateMaterial(lessonId, materialId, updatedMaterial));
    }

    @DeleteMapping("/api/lessons/{lessonId}/materials/{materialId}")
    public ResponseEntity<Void> deleteMaterial(
            @PathVariable Long lessonId,
            @PathVariable Long materialId
    ) {
        materialService.deleteMaterial(lessonId, materialId);
        return ResponseEntity.ok()
                .body(null);
    }
}