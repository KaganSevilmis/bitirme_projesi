package com.bitirmeprojesibackend.controller;

import com.bitirmeprojesibackend.model.CategoryModel;
import com.bitirmeprojesibackend.model.ProductModel;
import com.bitirmeprojesibackend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173") // Vue.js development server
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Ürün ID'si boş olamaz.");
            }

            ProductModel product = productService.getById(id);
            if (product != null) {
                return ResponseEntity.ok(product);
            }
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Ürün bulunamadı.");

        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ürün getirilirken bir hata oluştu: " + e.getMessage());
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> addProduct(@RequestBody ProductModel productModel) {
        try {
            if (productModel == null) {
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Ürün bilgileri boş olamaz.");
            }

            if (productModel.getName() == null || productModel.getName().trim().isEmpty()) {
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Ürün adı boş olamaz.");
            }

            ProductModel savedProduct = productService.add(productModel);
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedProduct);

        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ürün eklenirken bir hata oluştu: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<ProductModel> products = productService.getAll();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ürünler listelenirken bir hata oluştu: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Silinecek ürün ID'si boş olamaz.");
            }

            ProductModel product = productService.getById(id);
            if (product == null) {
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Silinecek ürün bulunamadı.");
            }

            productService.delete(id);
            return ResponseEntity.ok("Ürün başarıyla silindi.");

        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ürün silinirken bir hata oluştu: " + e.getMessage());
        }
    }
} 