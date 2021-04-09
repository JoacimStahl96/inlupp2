package com.example.demo.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

/*
    O.R.M CLASS
 */

@Entity(name="products")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @JsonProperty("product_id")
    @Column( length = 50, unique = true)
    private String productId;

    @Column(length = 100 ,nullable = false)
    @Size(min = 2, max = 100)
    private String name;

    @Column(precision = 2, scale = 2)
    @Min(1)
    private float cost;

    @Column(length = 120, nullable = false)
    @Size(min = 2, max = 120)
    private String category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
