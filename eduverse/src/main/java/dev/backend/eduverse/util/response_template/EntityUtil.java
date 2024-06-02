package dev.backend.eduverse.util.response_template;

import dev.backend.eduverse.exception.EntityCreationException;
import dev.backend.eduverse.exception.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class EntityUtil {

    public static <T> T saveEntity(JpaRepository<T, Long> repository, T entity, String entityName) {
        T savedEntity = repository.save(entity);
        if (savedEntity instanceof Identifiable && ((Identifiable) savedEntity).getId() == null) {
            throw new EntityCreationException("Failed to create the " + entityName);
        }
        return savedEntity;
    }

    public static <T> List<T> getAllEntities(JpaRepository<T, Long> repository, Sort sort, String entityName) {
        List<T> entities = repository.findAll(sort);
        if (entities.isEmpty()) {
            return null;
        }
        return entities;
    }

    public static <T> T getEntityById(JpaRepository<T, Long> repository, Long id) {
        T entity = id > 0 ? repository.findById(id).orElse(null) : null;
        if (entity == null) {
            throw new EntityNotFoundException("Entity not found with ID: " + id);
        }
        return entity;
    }


    public static <T> void deleteEntity(JpaRepository<T, Long> repository, Long id, String entityName) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(entityName + " not found");
        }
        repository.deleteById(id);
    }

    public interface Identifiable {
        Long getId();
    }
}