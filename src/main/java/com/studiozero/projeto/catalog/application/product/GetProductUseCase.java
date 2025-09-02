package com.studiozero.projeto.catalog.application.product;

import com.studiozero.projeto.catalog.domain.Product;
import com.studiozero.projeto.catalog.domain.ProductRepository;

public class GetProductUseCase {
    private final ProductRepository productRepository;

    public GetProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(Integer id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        return product;
    }
}
