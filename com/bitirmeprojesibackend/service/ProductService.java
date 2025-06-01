package com.bitirmeprojesibackend.service;


import com.bitirmeprojesibackend.model.CategoryModel;
import com.bitirmeprojesibackend.model.ProductModel;
import com.bitirmeprojesibackend.repository.CategoryRepository;
import com.bitirmeprojesibackend.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductModel getById(Long id) {
        try {
            Optional<ProductModel> product = productRepository.findById(id);
            return product.orElse(null);
        } catch (Exception e) {
            log.error("Error retrieving product with id: " + id, e);
            throw e;
        }
    }

    public ProductModel add(ProductModel productModel) {
        try {
            ProductModel savedProduct = productRepository.save(productModel);
            return savedProduct;
        } catch (Exception e) {
            log.error("Error saving product", e);
            throw e;
        }
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductModel> getAll() {
        return productRepository.findAll();
    }
} 