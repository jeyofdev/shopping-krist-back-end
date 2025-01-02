package com.jeyofdev.shopping_krist.core.interfaces.mapper;

/**
 * This interface is used to map the AuthUser's entity to the DTO
 *
 * @param <T> the entity
 * @param <D> the DTO
 */
public interface IAuthUserMapper<T, D> {
    D mapFromEntity(T entity);
}