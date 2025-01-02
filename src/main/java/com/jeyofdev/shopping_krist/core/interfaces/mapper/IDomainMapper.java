package com.jeyofdev.shopping_krist.core.interfaces.mapper;

/**
 * This interface is used to map the entity to the DTO and vice-versa
 *
 * @param <T> the entity
 * @param <D> the DTO
 * @param <S> the saveEntityDTO
 */
public interface IDomainMapper<T, D, S> {
    D mapFromEntity(T entity);

    default T mapToEntity(S saveEntityDTO) {
        throw new UnsupportedOperationException("This method is not implemented");
    }

    default T mapToEntity(S saveEntityDTO, T existingOrder) {
        return mapToEntity(saveEntityDTO);
    }

}