package dev.backend.eduverse.util.response_template;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    private static final Logger logger = LoggerFactory.getLogger(EntityMapper.class);

    /**
     * @param dto
     * @param entity
     * @param <T>    Type of the DTO Entity
     * @param <U>    Type of the Entity
     * @return
     */
    public <T, U> T mapDTOtoEntity(U dto, T entity) {

        /**
         * @USAGE by utilizing the reflection API, Entity Creation can be reusable @Implementaion
         * Iterate through the Fields of the Object, open the stream and filter by equal Field checking
         * Using the findFirst Stream API Method, and check the null
         */
        Field[] dtoFields = dto.getClass().getDeclaredFields();
        Field[] entityFields = entity.getClass().getDeclaredFields();
        for (Field dtoField : dtoFields) {
            try {
                Field entityFiled =
                        Arrays.stream(entityFields)
                                .filter(f -> f.getName().equals(dtoField.getName()))
                                .findFirst()
                                .orElse(null);
                /** surpass the language Access checking, set the field by field */
                if (entityFiled != null) {
                    dtoField.setAccessible(true);
                    entityFiled.setAccessible(true);
                    entityFiled.set(entity, dtoField.get(dto));
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }
        }
        return entity;
    }
}
