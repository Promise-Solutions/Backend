package com.studiozero.projeto.services;

import com.studiozero.projeto.application.services.ProductService;
import com.studiozero.projeto.application.services.TracingService;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.application.services.enums.Context;
import com.studiozero.projeto.infrastructure.configs.exceptions.NotFoundException;
import com.studiozero.projeto.domain.entities.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private TracingService tracingService;

    private Product validProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        validProduct = Product.builder()
                .id(1)
                .name("Coca-Cola")
                .clientValue(5.0)
                .internalValue(3.0)
                .quantity(10)
                .build();
    }

    @Test
    @DisplayName("Should create a new product with quantity 0 and set tracing")
    void createProduct_shouldCreateNewProductWithZeroQuantityAndTracing() {
        Product newProduct = Product.builder()
                .name("Pepsi")
                .clientValue(6.0)
                .internalValue(4.0)
                .build();

        Product savedProduct = Product.builder()
                .id(2)
                .name("Pepsi")
                .clientValue(6.0)
                .internalValue(4.0)
                .quantity(0)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        Product result = productService.createProduct(newProduct);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(2);
        assertThat(result.getQuantity()).isZero();
        verify(tracingService).setTracing(Context.BAR);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    @DisplayName("Should throw exception when client value is invalid")
    void createProduct_shouldThrowExceptionWhenClientValueIsInvalid() {
        Product invalid = Product.builder()
                .name("Refri")
                .clientValue(0.0)
                .internalValue(4.0)
                .build();

        assertThatThrownBy(() -> productService.createProduct(invalid))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Client value should be greater than zero");
    }

    @Test
    @DisplayName("Should throw exception when internal value is invalid")
    void createProduct_shouldThrowExceptionWhenInternalValueIsInvalid() {
        Product invalid = Product.builder()
                .name("Refri")
                .clientValue(5.0)
                .internalValue(0.0)
                .build();

        assertThatThrownBy(() -> productService.createProduct(invalid))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Internal value should be greater than zero");
    }

    @Test
    @DisplayName("Should return product by ID when found")
    void findProductById_shouldReturnProductWhenFound() {
        when(productRepository.findById(1)).thenReturn(Optional.of(validProduct));

        Product found = productService.findProductById(1);

        assertThat(found).isEqualTo(validProduct);
    }

    @Test
    @DisplayName("Should throw exception when product not found by ID")
    void findProductById_shouldThrowExceptionWhenNotFound() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.findProductById(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Product not found");
    }

    @Test
    @DisplayName("Should return all products")
    void listProducts_shouldReturnAllProducts() {
        List<Product> products = List.of(validProduct);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.listProducts();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(validProduct);
    }

    @Test
    @DisplayName("Should update product when it exists")
    void updateProduct_shouldUpdateProductWhenExists() {
        when(productRepository.existsById(validProduct.getId())).thenReturn(true);
        when(productRepository.save(any(Product.class))).thenReturn(validProduct);

        Product updated = productService.updateProduct(validProduct);

        assertThat(updated).isEqualTo(validProduct);
        verify(tracingService).setTracing(Context.BAR);
        verify(productRepository).save(validProduct);
    }

    @Test
    @DisplayName("Should throw exception when trying to update nonexistent product")
    void updateProduct_shouldThrowExceptionWhenNotFound() {
        when(productRepository.existsById(validProduct.getId())).thenReturn(false);

        assertThatThrownBy(() -> productService.updateProduct(validProduct))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Product not found");
    }

    @Test
    @DisplayName("Should throw exception when updating product with invalid values")
    void updateProduct_shouldThrowExceptionWhenInvalidValues() {
        Product invalid = Product.builder()
                .id(1)
                .name("Refri")
                .clientValue(null)
                .internalValue(4.0)
                .build();

        when(productRepository.existsById(invalid.getId())).thenReturn(true);

        assertThatThrownBy(() -> productService.updateProduct(invalid))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Client value should be greater than zero");
    }

    @Test
    @DisplayName("Should delete product when it exists")
    void deleteProduct_shouldDeleteWhenExists() {
        when(productRepository.existsById(1)).thenReturn(true);

        productService.deleteProduct(1);

        verify(tracingService).setTracing(Context.BAR);
        verify(productRepository).deleteById(1);
    }

    @Test
    @DisplayName("Should throw exception when deleting nonexistent product")
    void deleteProduct_shouldThrowExceptionWhenNotFound() {
        when(productRepository.existsById(1)).thenReturn(false);

        assertThatThrownBy(() -> productService.deleteProduct(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Product not found");
    }
}
