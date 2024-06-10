/*
 * @Author : Alvin
 * @Date : 6/9/2024
 * @Time : 10:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.util.response_template;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.backend.eduverse.exception.EntityCreationException;
import dev.backend.eduverse.exception.EntityNotFoundException;

import java.util.List;

public class EntityUtil {

    public static <T> T saveEntity(JpaRepository<T, Long> repository, T entity, String entityName) {
        T savedEntity = repository.save(entity);
        if (savedEntity instanceof Identifiable && ((Identifiable) savedEntity).getId() == null) {
            throw new EntityCreationException("Failed to create the " + entityName);
        }
        return savedEntity;
    }

    public static <T> List<T> getAllEntities(JpaRepository<T, Long> repository) {
        List<T> entities = repository.findAll();
        if (entities.isEmpty()) {
            return null;
        }
        return entities;
    }

    public static <T> T getEntityById(JpaRepository<T, Long> repository, Long id, String entityName) {
        T entity = id > 0 ? repository.findById(id).orElse(null) : null;
        if (entity == null) {
            throw new EntityNotFoundException(entityName + " not found with ID: " + id);
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