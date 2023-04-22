package com.senbol.seda.youtubeclone.common.util;

import com.senbol.seda.youtubeclone.error.EntityNotSavedException;
import com.senbol.seda.youtubeclone.error.NullFieldException;
import com.senbol.seda.youtubeclone.model.BaseEntity;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Objects;

public class CommonUtils {

    public static <T> void checkNullFields(T entity) throws IllegalAccessException, NullFieldException {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (!field.trySetAccessible() || !field.isAnnotationPresent(NotNull.class)) {
                continue;
            }
            if (Objects.isNull(field.get(entity))) {
                throw new NullFieldException(String.format("checkNullFields:: Entity has a null field with @NotNull annotation. Entity: %s Field: %s", entity, field));
            }
        }
    }

    public static <T extends BaseEntity> T checkSavedEntity(T entity, String entityClassName) throws EntityNotSavedException {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            throw new EntityNotSavedException(String.format("checkSavedEntity:: entity could not be saved to the repository. EntityClassName: %s", entityClassName));
        }
        return entity;
    }

}
