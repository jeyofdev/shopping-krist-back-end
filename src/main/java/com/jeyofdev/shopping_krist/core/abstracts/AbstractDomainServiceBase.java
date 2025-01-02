package com.jeyofdev.shopping_krist.core.abstracts;

import com.jeyofdev.shopping_krist.core.interfaces.service.IBaseCrudGetService;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
import com.jeyofdev.shopping_krist.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class AbstractDomainServiceBase<T, R extends JpaRepository<T, UUID>> implements IBaseCrudGetService<T> {
    protected final R repository;
    private final String entityName;

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(UUID entityId) throws NotFoundException {
        return repository.findById(entityId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format("Entity {0} with id '{'0'}' cannot be found", Helper.capitalizeFirstLetter(entityName))));
    }
}
