package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.web.dtos.request.ProductRequestDTO;
import com.studiozero.projeto.web.dtos.response.ProductResponseDTO;
import com.studiozero.projeto.web.mappers.ProductMapper;
import com.studiozero.projeto.application.usecases.product.CreateProductUseCase;
import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.application.usecases.product.UpdateProductUseCase;
import com.studiozero.projeto.application.usecases.product.DeleteProductUseCase;
import com.studiozero.projeto.application.usecases.product.ListProductsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "Endpoints for Product Management")
public class ProductController {

        private final CreateProductUseCase createProductUseCase;
        private final GetProductUseCase getProductUseCase;
        private final UpdateProductUseCase updateProductUseCase;
        private final DeleteProductUseCase deleteProductUseCase;
        private final ListProductsUseCase listProductsUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, GetProductUseCase getProductUseCase, UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase, ListProductsUseCase listProductsUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getProductUseCase = getProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.listProductsUseCase = listProductsUseCase;
    }

    @Operation(summary = "Create a product", description = "This method is responsible for create a product.")
        @PostMapping
        public ResponseEntity<ProductResponseDTO> createProduct(
                        @RequestBody @Valid ProductRequestDTO productDto) {
                Product product = ProductMapper.toDomain(productDto);
                Product savedProduct = createProductUseCase.execute(product);
                return ResponseEntity.status(201).body(ProductMapper.toDTO(savedProduct));
        }

        @Operation(summary = "Search a product", description = "This method is responsible for search a product.")
        @GetMapping("/{id}")
        public ResponseEntity<ProductResponseDTO> findProductById(
                        @PathVariable @Valid Integer id) {
                Product product = getProductUseCase.execute(id);
                return ResponseEntity.ok(ProductMapper.toDTO(product));
        }

        @Operation(summary = "List all products", description = "This method is responsible for list all products.")
        @GetMapping
        public ResponseEntity<List<ProductResponseDTO>> findAllProducts() {
                List<Product> products = listProductsUseCase.execute();
                if (products.isEmpty()) {
                        return ResponseEntity.status(204).build();
                }
                List<ProductResponseDTO> dtos = ProductMapper.toDTOList(products);
                return ResponseEntity.status(200).body(dtos);
        }

        @Operation(summary = "Update a product", description = "This method is responsible for update a product.")
        @PatchMapping("/{id}")
        public ResponseEntity<ProductResponseDTO> updateProduct(
                        @PathVariable @Valid Integer id,
                        @RequestBody @Valid ProductRequestDTO productDto) {
                Product product = ProductMapper.toDomain(productDto, id);
                Product updatedProduct = updateProductUseCase.execute(product);
                return ResponseEntity.ok(ProductMapper.toDTO(updatedProduct));
        }

        @Operation(summary = "Delete a product", description = "This method is responsible for delete a product.")
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteProduct(
                        @PathVariable Integer id) {
                deleteProductUseCase.execute(id);
                return ResponseEntity.ok().build();
        }
}
