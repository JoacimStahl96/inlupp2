package com.example.demo.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/*
    O.R.M CLASS
 */

@Entity(name="products")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 50, unique = true, nullable = false)
    private String product_id;

    @Column(length = 100)
    private String name;

    @Column(precision = 2, scale = 2)
    private float cost;

    @Column(length = 120)
    private String category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
