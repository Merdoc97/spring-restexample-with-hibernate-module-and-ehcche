package com.example.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.util.List;

/**
 * Created by artyo on 06.05.2016.
 */
@Entity
@Cacheable
@Table(name = "items")
public class Items implements GeneralEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "item_name",nullable = false)
    private String name;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId",referencedColumnName = "id",insertable = false,updatable = false)
    private List<ItemAttributes>attributes;

    public List<ItemAttributes> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ItemAttributes> attributes) {
        this.attributes = attributes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Items)) return false;

        Items items = (Items) o;

        if (!id.equals(items.id)) return false;
        return name.equals(items.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
