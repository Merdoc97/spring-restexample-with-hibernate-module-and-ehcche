package com.example.model;

import io.swagger.models.auth.In;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by artyo on 18.05.2016.
 */
@Entity
@Table(name = "item_attr_dep")
public class ItemAttrDep implements GeneralEntity {
    @Id
    @Column(name = "attr_dep_id")
    private Integer id;
    @Column(name = "dep_value")
    private String value;
    @Column(name = "attrId")
    private Integer attrId;





    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemAttrDep)) return false;

        ItemAttrDep that = (ItemAttrDep) o;

        if (!id.equals(that.id)) return false;
        return value.equals(that.value);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
