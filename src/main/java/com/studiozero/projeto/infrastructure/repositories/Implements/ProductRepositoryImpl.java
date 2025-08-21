package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.domain.repositories.ProductRepository;
import com.studiozero.projeto.infrastructure.repositories.JpaProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;

    @Autowired
    public ProductRepositoryImpl(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public Product findById(Integer id) {
        return jpaProductRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        jpaProductRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        jpaProductRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll();
    }
}
