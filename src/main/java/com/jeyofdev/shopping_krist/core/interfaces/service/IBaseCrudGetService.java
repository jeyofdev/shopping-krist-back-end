package com.jeyofdev.shopping_krist.core.interfaces.service;

import com.jeyofdev.shopping_krist.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * The interface for the base crud get service
 * 
 * @param <T> the entity
 */
public interface IBaseCrudGetService<T> {
        List<T> findAll();

        T findById(UUID entityId) throws NotFoundException;
}
