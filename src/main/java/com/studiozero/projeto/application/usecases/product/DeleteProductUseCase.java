package com.studiozero.projeto.application.usecases.product;

import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.domain.repositories.ProductRepository;

public class DeleteProductUseCase {
    private final ProductRepository productRepository;

    public DeleteProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(Integer id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        productRepository.deleteById(id);
    }
}
