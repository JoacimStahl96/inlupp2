package com.example.demo.service.impl;

import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.entity.ProductEntity;
import com.example.demo.service.ProductService;
import com.example.demo.shared.dto.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductEntity> getProduct() {
        if (productRepository.findAll().size() > 0) {
            return productRepository.findAll();
        } else {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Selected table is empty");}
    }

    public Optional<ProductEntity> getSpecificProduct(String productId) {
        if (productRepository.findByProductId(productId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, productId  + " doesn't exist");
        }
        return productRepository.findByProductId(productId);
    }


    public ProductDto createProduct(ProductDto productDetails) {
        Optional<ProductEntity> checkProductIdEntity = productRepository.findByProductId(productDetails.getProductId());
        if (checkProductIdEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists");
        }
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDetails, productEntity);
        ProductEntity productEntityOut = productRepository.save(productEntity);

        ProductDto productDtoOut = new ProductDto();
        BeanUtils.copyProperties(productEntityOut, productDtoOut);

        return productDtoOut;
    }


    public String updateProduct() {
        return "updateProduct()";
    }


    public String deleteProduct() {
        return "deleteProduct()";
    }
}
