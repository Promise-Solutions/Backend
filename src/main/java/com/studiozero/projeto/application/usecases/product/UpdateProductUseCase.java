package com.studiozero.projeto.application.usecases.product;

import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.domain.repositories.ProductRepository;

public class UpdateProductUseCase {
    private final ProductRepository productRepository;

    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(Product product) {
        if (product == null || product.getId() == null || product.getId().toString().isEmpty()) {
            throw new IllegalArgumentException("Produto inválido");
        }
        Product existing = productRepository.findById(product.getId());
        if (existing == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        validateProductsValues(product);
        productRepository.save(product);
        return product;
    }

    private void validateProductsValues(Product product) {
        if (product.getClientValue() == null || product.getClientValue() <= 0) {
            throw new IllegalArgumentException("Client value should be greater than zero");
        }
        if (product.getInternalValue() == null || product.getInternalValue() <= 0) {
            throw new IllegalArgumentException("Internal value should be greater than zero");
        }
    }
}
