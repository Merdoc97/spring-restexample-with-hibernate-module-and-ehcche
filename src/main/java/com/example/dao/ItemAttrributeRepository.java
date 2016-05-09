package com.example.dao;

import com.example.model.ItemAttributes;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by artyo on 08.05.2016.
 */
@Repository
public interface ItemAttrributeRepository extends CrudRepository<ItemAttributes,Integer>{

    @Override
    @Cacheable("attributes")
    Iterable<ItemAttributes> findAll();
}
