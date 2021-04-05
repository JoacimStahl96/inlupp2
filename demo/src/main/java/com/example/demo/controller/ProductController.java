package com.example.demo.controller;

import com.example.demo.model.request.ProductDetailsRequestModel;
import com.example.demo.model.response.ProductResponseModel;
import com.example.demo.repository.entity.ProductEntity;
import com.example.demo.shared.dto.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products") // localhost:8080/products
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    List<ProductEntity> getProduct() {
       return productService.getProduct();
    }
    /*@GetMapping(path = "{product_id}")
    public Optional<ProductEntity> getSpecificProduct(@PathVariable("product_id") String product_id){
        return productService.getSpecificProduct(product_id);
    }*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseModel createProduct(@RequestBody ProductDetailsRequestModel productDetailsModel) {
        // copy json to dto in
        ProductDto productDtoIn = new ProductDto();
        BeanUtils.copyProperties(productDetailsModel, productDtoIn);

        // pass dto in to service layer
        ProductDto productDtoOut = productService.createProduct(productDtoIn);

        // copy dto out from service layer to response
        ProductResponseModel response = new ProductResponseModel();
        BeanUtils.copyProperties(productDtoOut, response);

        return response;
    }

    @PutMapping
    String updateProduct() {
        return productService.updateProduct();
    }

    @DeleteMapping
    String deleteProduct() {
        return productService.deleteProduct();
    }
}
