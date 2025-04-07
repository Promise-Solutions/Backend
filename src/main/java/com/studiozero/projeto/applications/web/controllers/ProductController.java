package com.studiozero.projeto.applications.web.controllers;

import com.studiozero.projeto.applications.web.dtos.request.ProductCreateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ProductDeleteRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ProductUpdateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.response.ProductResponseDTO;
import com.studiozero.projeto.domain.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<ProductResponseDTO> saveProduct(
            @RequestBody ProductCreateRequestDTO productDto
    ) {
        return ResponseEntity.ok(productService.save(productDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> listProducts() {
        return ResponseEntity.ok(productService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PatchMapping
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ProductUpdateRequestDTO productDto) {
        return ResponseEntity.ok(productService.update(productDto));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(ProductDeleteRequestDTO productDto) {
        return ResponseEntity.ok(productService.delete(productDto));
    }
}
