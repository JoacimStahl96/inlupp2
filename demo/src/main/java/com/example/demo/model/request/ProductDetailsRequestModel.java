package com.example.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class ProductDetailsRequestModel {
    @JsonProperty("product_id")
    private String productId;
    @Size(min = 2, max = 100)
    private String name;
    @Size(min = 2, max = 120)
    private String category;

    @Min(1)
    private float cost;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
