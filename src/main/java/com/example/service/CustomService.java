package com.example.service;

import com.example.dao.ItemRepository;
import com.example.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by artyo on 18.05.2016.
 */
@Service
@Transactional(readOnly = true)
public class CustomService {
    @Autowired
    @Qualifier("itemRepository")
    private ItemRepository itemRepository;
    @Transactional(readOnly = true)
    public List<Items> getAllWithDep(){
        List<Items>res= (List<Items>) itemRepository.findAll();
        /*for (Items items:res){
            for (ItemAttributes attributes:items.getAttributes())
                Hibernate.initialize(attributes.getAttrDeps());

        }*/
        return res;
    }
}
