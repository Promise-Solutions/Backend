package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.domain.repositories.ProductRepository;
import com.studiozero.projeto.infrastructure.entities.ProductEntity;
import com.studiozero.projeto.infrastructure.mappers.ProductEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.JpaProductRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;

    @Override
    public Product findById(Integer id) {
        return jpaProductRepository.findById(id)
            .map(ProductEntityMapper::toDomain)
            .orElse(null);
    }

    @Override
    public void save(Product product) {
        ProductEntity entity = ProductEntityMapper.toEntity(product);
        jpaProductRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        jpaProductRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll()
            .stream()
            .map(ProductEntityMapper::toDomain)
            .toList();
    }
}
