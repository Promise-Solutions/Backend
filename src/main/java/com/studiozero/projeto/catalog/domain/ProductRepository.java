package com.studiozero.projeto.catalog.domain;

import java.util.List;

public interface ProductRepository {
    Product findById(Integer id);

    void save(Product product);

    void deleteById(Integer id);

    List<Product> findAll();
}
