package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(Integer id);

    void save(Product product);

    void deleteById(Integer id);

    List<Product> findAll();
}
