package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.ProductRequestDTO;
import com.studiozero.projeto.dtos.response.ProductResponseDTO;
import com.studiozero.projeto.entities.Product;
import com.studiozero.projeto.mappers.ProductMapper;
import com.studiozero.projeto.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Endpoints for Product Management")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(
            summary = "Create a product",
            description = "This method is responsible for create a product."
    )
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestBody @Valid ProductRequestDTO productDto
    ) {
        Product product = productMapper.toEntity(productDto);
        Product savedProduct = productService.createProduct(product);

        return ResponseEntity.status(201).body(ProductMapper.toDTO(savedProduct));
    }

    @Operation(
            summary = "Search a product",
            description = "This method is responsible for search a product."
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findProductById(
            @PathVariable @Valid Integer id
    ) {
        Product product = productService.findProductById(id);
        return ResponseEntity.ok(ProductMapper.toDTO(product));
    }

    @Operation(
            summary = "List all products",
            description = "This method is responsible for list all products."
    )
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAllProducts() {
        List<Product> products = productService.listProducts();

        if (products.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<ProductResponseDTO> dtos = ProductMapper.toListDtos(products);


        return ResponseEntity.status(200).body(dtos);
    }

    @Operation(
            summary = "Update a product",
            description = "This method is responsible for update a product."
    )
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable @Valid Integer id,
            @RequestBody @Valid ProductRequestDTO productDto
    ) {
        Product product = productMapper.toEntity(productDto, id);
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(ProductMapper.toDTO(updatedProduct));
    }

    @Operation(
            summary = "Delete a product",
            description = "This method is responsible for delete a product."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Integer id
    ) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
