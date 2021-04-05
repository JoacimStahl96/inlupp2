package com.example.demo.service;

import com.example.demo.repository.entity.ProductEntity;
import com.example.demo.shared.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {
     List<ProductEntity> getProduct();
     Optional<ProductEntity> getSpecificProduct(String product_id);
     ProductDto createProduct(ProductDto productDetails);
     String updateProduct();
     String deleteProduct();

}
