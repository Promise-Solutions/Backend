package com.studiozero.projeto.catalog.application.product;

import com.studiozero.projeto.catalog.domain.Product;
import com.studiozero.projeto.catalog.domain.ProductRepository;

public class CreateProductUseCase {
    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Produto inválido");
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
