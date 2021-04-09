package com.example.demo.repository;

import com.example.demo.repository.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByProductId(String productId);

    List<ProductEntity> findAll();
}
