package com.example.demo.service.impl;

import com.example.demo.model.response.ProductResponseModel;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.entity.ProductEntity;
import com.example.demo.service.ProductService;
import com.example.demo.shared.Util;
import com.example.demo.shared.dto.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Util util;

    ProductServiceImpl(ProductRepository productRepository, Util util) {
        this.productRepository = productRepository;
        this.util = util;
    }

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

        String productId = util.generateHash(productDetails.getName());
        productEntity.setProductId(productId.substring(3));

        ProductEntity productEntityOut = productRepository.save(productEntity);

        ProductDto productDtoOut = new ProductDto();
        BeanUtils.copyProperties(productEntityOut, productDtoOut);

        return productDtoOut;
    }

    public Optional<ProductDto> updateProduct(String product_id, ProductDto productDto) {
        Optional<ProductEntity> productIdEntity = productRepository.findByProductId(product_id);

        if (productIdEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters");
        }

        return productIdEntity.map(productEntity -> {
           ProductDto response = new ProductDto();

           // if values coming in are not null then update - product_id won't be updated at all even when the name changes
           productEntity.setName(productDto.getName() != null ? productDto.getName() : productEntity.getName());
            productEntity.setCost(productDto.getCost() != 0 ? productDto.getCost() : productEntity.getCost());
            productEntity.setCategory(productDto.getCategory() != null ? productDto.getCategory() : productEntity.getCategory());

           ProductEntity updatedProductEntity = productRepository.save(productEntity);
           BeanUtils.copyProperties(updatedProductEntity, response);
           return response;
        });
    }

    public void deleteProduct(String productId) {

        Optional<ProductEntity> prodEntityExists = productRepository.findByProductId(productId);
        if(prodEntityExists.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product doesn't exists");
        }
        productRepository.delete(prodEntityExists.get());
        // if line below is activated we get a confirmation returned of deleted product in postman
    //    throw new ResponseStatusException(HttpStatus.OK, "Product with product_id " + productId + " deleted");
    }
}
