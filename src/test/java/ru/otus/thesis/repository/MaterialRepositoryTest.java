package ru.otus.thesis.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import ru.otus.thesis.model.Lesson;
import ru.otus.thesis.model.Material;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MaterialRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Test
    void whenFindByLessonId_thenReturnMaterials() {
        Lesson lesson1 = lessonRepository.getReferenceById(1L);
        Lesson lesson2 = lessonRepository.getReferenceById(2L);

        Material material1 = new Material()
                .setLesson(lesson1)
                .setTitle("Material 1");
        Material material2 = new Material()
                .setLesson(lesson1)
                .setTitle("Material 2");
        Material materialOtherLesson = new Material()
                .setLesson(lesson2)
                .setTitle("Other Lesson Material");

        entityManager.persist(material1);
        entityManager.persist(material2);
        entityManager.persist(materialOtherLesson);
        entityManager.flush();

        List<Material> foundMaterials = materialRepository.findByLessonId(lesson1.getId());

        assertThat(foundMaterials).hasSize(2)
                .extracting(Material::getTitle)
                .containsExactlyInAnyOrder("Material 1", "Material 2");
    }

    @Test
    void whenFindByNonExistingLessonId_thenReturnEmptyList() {
        Lesson lesson = lessonRepository.getReferenceById(3L);

        Material material = new Material()
                .setLesson(lesson)
                .setTitle("Test Material");
        entityManager.persist(material);
        entityManager.flush();

        List<Material> foundMaterials = materialRepository.findByLessonId(99L);

        assertThat(foundMaterials).isEmpty();
    }

    @Test
    void whenSaveMaterial_thenCanRetrieveIt() {
        Lesson lesson = lessonRepository.getReferenceById(4L);

        Material material = new Material()
                .setLesson(lesson)
                .setTitle("New Material")
                .setDescription("Description")
                .setUrl("http://example.com");

        Material saved = materialRepository.save(material);
        Material found = entityManager.find(Material.class, saved.getId());

        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("New Material");
        assertThat(found.getLesson().getId()).isEqualTo(4L);
    }

    @Test
    void whenUpdateMaterial_thenChangesPersisted() {
        Lesson lesson = lessonRepository.getReferenceById(5L);

        Material material = new Material()
                .setLesson(lesson)
                .setTitle("Original Title");
        entityManager.persist(material);
        entityManager.flush();

        material.setTitle("Updated Title")
                .setDescription("New Description");
        materialRepository.save(material);
        entityManager.flush();
        entityManager.clear();

        Material updated = entityManager.find(Material.class, material.getId());

        assertThat(updated.getTitle()).isEqualTo("Updated Title");
        assertThat(updated.getDescription()).isEqualTo("New Description");
    }

    @Test
    void whenDeleteMaterial_thenCannotFindIt() {
        Lesson lesson = lessonRepository.getReferenceById(6L);

        Material material = new Material()
                .setLesson(lesson)
                .setTitle("To be deleted");
        entityManager.persist(material);
        entityManager.flush();

        materialRepository.deleteById(material.getId());
        entityManager.flush();

        assertThat(entityManager.find(Material.class, material.getId())).isNull();
    }

    @Test
    void whenSaveWithoutRequiredFields_thenThrowException() {
        Lesson lesson = lessonRepository.getReferenceById(7L);
        lesson.setId(1000L);

        Material material = new Material()
                .setLesson(lesson);

        assertThrows(DataIntegrityViolationException.class, () -> {
            materialRepository.saveAndFlush(material);
        });
    }

    @Test
    void givenMaterialsExist_whenCountByLessonId_thenReturnCorrectCount() {
        Lesson lesson1 = lessonRepository.getReferenceById(8L);
        Lesson lesson2 = lessonRepository.getReferenceById(9L);

        entityManager.persist(new Material().setLesson(lesson1).setTitle("M1"));
        entityManager.persist(new Material().setLesson(lesson1).setTitle("M2"));
        entityManager.persist(new Material().setLesson(lesson2).setTitle("M3"));
        entityManager.flush();

        // when
        long count = materialRepository.countByLessonId(lesson1.getId());

        // then
        assertThat(count).isEqualTo(2);
    }
}