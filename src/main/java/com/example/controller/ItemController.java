package com.example.controller;

import com.example.model.ItemAttributes;
import com.example.model.Items;
import com.example.service.CustomService;
import com.example.service.GeneralService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author I.Artyomov
 * @apiNote basic example controller
 */
@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    @Qualifier(value = "itemService")
    GeneralService generalService;

    @Autowired
    @Qualifier("itemAttribute")
    GeneralService attributeService;

    @Autowired
    @Qualifier("customService")
    private CustomService customService;

    @RequestMapping(value = "/withdep/",method = RequestMethod.GET)
    public List<Items>getAllwithDep(){
        List<Items>res=customService.getAllWithDep();

        return res;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Items> getAllItems() {
        return generalService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Items getById(@PathVariable(value = "id") Integer id) {
        return (Items) generalService.getById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Items save(@RequestBody Items items) throws NotFoundException {
        return (Items) generalService.save(items);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Items update(@RequestBody Items items) throws NotFoundException {
        return (Items) generalService.update(items);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpStatus delete(@PathVariable(value = "id") Integer id) {
        generalService.delete(id);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/attr/",method = RequestMethod.GET)
    public List<ItemAttributes> getAllAttr(){
        return attributeService.getAll();
    }
}
