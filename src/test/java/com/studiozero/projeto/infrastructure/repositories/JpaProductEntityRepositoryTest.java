package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.infrastructure.entities.ProductEntity;
import com.studiozero.projeto.infrastructure.mappers.ProductEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.Implements.ProductRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JpaProductEntityRepositoryTest {

    private JpaProductRepository jpaProductRepository;
    private ProductRepositoryImpl productRepository;

    @BeforeEach
    void setUp() {
        jpaProductRepository = mock(JpaProductRepository.class);
        productRepository = new ProductRepositoryImpl(jpaProductRepository);
    }

    @Test
    void testFindByIdSuccess() {
        Integer id = 1;
        ProductEntity entity = mock(ProductEntity.class);
        Product product = mock(Product.class);

        when(jpaProductRepository.findById(id)).thenReturn(Optional.of(entity));
        try (MockedStatic<ProductEntityMapper> mocked = mockStatic(ProductEntityMapper.class)) {
            mocked.when(() -> ProductEntityMapper.toDomain(entity)).thenReturn(product);

            Product result = productRepository.findById(id);
            assertEquals(product, result);
        }
    }

    @Test
    void testFindByIdNotFound() {
        Integer id = 1;
        when(jpaProductRepository.findById(id)).thenReturn(Optional.empty());

        Product result = productRepository.findById(id);
        assertNull(result);
    }

    @Test
    void testSave() {
        Product product = mock(Product.class);
        ProductEntity entity = mock(ProductEntity.class);

        try (MockedStatic<ProductEntityMapper> mocked = mockStatic(ProductEntityMapper.class)) {
            mocked.when(() -> ProductEntityMapper.toEntity(product)).thenReturn(entity);

            productRepository.save(product);
            verify(jpaProductRepository).save(entity);
        }
    }

    @Test
    void testDeleteById() {
        Integer id = 1;
        productRepository.deleteById(id);
        verify(jpaProductRepository).deleteById(id);
    }

    @Test
    void testFindAll() {
        ProductEntity entity = mock(ProductEntity.class);
        Product product = mock(Product.class);

        when(jpaProductRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<ProductEntityMapper> mocked = mockStatic(ProductEntityMapper.class)) {
            mocked.when(() -> ProductEntityMapper.toDomain(entity)).thenReturn(product);

            List<Product> result = productRepository.findAll();
            assertEquals(1, result.size());
            assertEquals(product, result.get(0));
        }
    }
}