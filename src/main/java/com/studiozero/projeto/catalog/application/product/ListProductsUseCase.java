package com.studiozero.projeto.catalog.application.product;

import com.studiozero.projeto.catalog.domain.Product;
import com.studiozero.projeto.catalog.domain.ProductRepository;
import java.util.List;

public class ListProductsUseCase {
    private final ProductRepository productRepository;

    public ListProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> execute() {
        return productRepository.findAll();
    }
}
