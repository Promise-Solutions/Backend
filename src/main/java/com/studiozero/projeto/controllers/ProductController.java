package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.ProductRequestDTO;
import com.studiozero.projeto.dtos.response.ProductResponseDTO;
import com.studiozero.projeto.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "Endpoints for Product Management")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestBody @Valid ProductRequestDTO productDto
    ) {
        return ResponseEntity.ok(productService.save(productDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Integer id,
            @RequestBody @Valid ProductRequestDTO productDto
    ) {
        return ResponseEntity.ok(productService.update(id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(Integer id) {
        return ResponseEntity.ok(productService.delete(id));
    }
}
