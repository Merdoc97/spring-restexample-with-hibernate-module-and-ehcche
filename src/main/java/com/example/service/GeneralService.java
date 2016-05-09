package com.example.service;

import com.example.model.GeneralEntity;
import javassist.NotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Artyomov.I
 *         abstract service for common CRUD operations
 *         with second level cache.
 *         File should looking into woth ehcache.xml and appconfig.xml.
 *         Cache used Ehcache @see http://www.ehcache.org/
 */

public class GeneralService<T> {

    private final CrudRepository<T, Number> repository;

    public GeneralService(CrudRepository<T, Number> repository) {
        this.repository = repository;
    }

    /**
     * @return entity which registered in repository
     */
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return (List<T>) repository.findAll();
    }

    /**
     * @param id entity id in repository
     * @return entity registered in repository
     */
    @Transactional(readOnly = true)
    public T getById(Number id) {
        return repository.findOne(id);
    }

    @Transactional(rollbackFor = Exception.class)
    //clear entity from cache by id after update
    public T update(T entity) throws NotFoundException {
        GeneralEntity generalEntity = (GeneralEntity) entity;
        if (generalEntity.getId() == null) {
            throw new NotFoundException("entity should have id for update method");
        }
        return repository.save(entity);

    }

    @Transactional(rollbackFor = Exception.class)
    public T save(T entity) throws NotFoundException {
        GeneralEntity generalEntity = (GeneralEntity) entity;
        if (generalEntity.getId() != null) {
            throw new NotFoundException("entity should have id for update method");
        }
        return repository.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Number id) {
        repository.delete(id);
    }
}
