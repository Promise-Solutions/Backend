package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.ProductRequestDTO;
import com.studiozero.projeto.dtos.response.ProductResponseDTO;
import com.studiozero.projeto.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
            summary = "Create a product",
            description = "This method is responsible for create a product."
    )
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestBody @Valid ProductRequestDTO productDto
    ) {
        return ResponseEntity.ok(productService.save(productDto));
    }

    @Operation(
            summary = "Search a product",
            description = "This method is responsible for search a product."
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @Operation(
            summary = "List a products",
            description = "This method is responsible for list a products."
    )
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @Operation(
            summary = "Update a product",
            description = "This method is responsible for update a product."
    )
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Integer id,
            @RequestBody @Valid ProductRequestDTO productDto
    ) {
        return ResponseEntity.ok(productService.update(id, productDto));
    }

    @Operation(
            summary = "Delete a product",
            description = "This method is responsible for delete a product."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(Integer id) {
        return ResponseEntity.ok(productService.delete(id));
    }
}
