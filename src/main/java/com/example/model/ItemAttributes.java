package com.example.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by artyo on 08.05.2016.
 */
@Entity
@org.springframework.cache.annotation.Cacheable
@Table(name = "item_attr")
public class ItemAttributes implements GeneralEntity{
    @Id
    @GeneratedValue
    @Column(name = "attr_id")
    private Integer id;

    @Column(name = "itemId",nullable = false)
    private Integer itemId;

    @Column(name = "attr_value")
    private String value;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "attributes_attr_id",referencedColumnName = "attr_id")
    private List<ItemAttrDep>attrDeps;

    public Integer getItemId() {
        return itemId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItemAttrDep> getAttrDeps() {
        return attrDeps;
    }

    public void setAttrDeps(List<ItemAttrDep> attrDeps) {
        this.attrDeps = attrDeps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemAttributes)) return false;

        ItemAttributes that = (ItemAttributes) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
