package com.studiozero.projeto.catalog.infrastruture.repository;

import com.studiozero.projeto.catalog.domain.Product;
import com.studiozero.projeto.catalog.domain.ProductRepository;
import com.studiozero.projeto.catalog.infrastruture.entity.ProductEntity;
import com.studiozero.projeto.catalog.infrastruture.mapper.ProductEntityMapper;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

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
