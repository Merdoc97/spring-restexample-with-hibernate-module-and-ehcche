package com.example.dao;

import com.example.model.Items;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author I.Artyomov
 *
 */
@Repository
public interface ItemRepository extends CrudRepository<Items,Integer> {

    @Override
    @Cacheable("entities")
    Iterable<Items> findAll();

    @Override
    @Query("select item from Items item left join fetch item.attributes where item.id=:id")
    Items findOne(@Param("id") Integer integer);
}
