
package com.studiozero.projeto.catalog.application.product;


import com.studiozero.projeto.catalog.domain.Product;
import com.studiozero.projeto.catalog.domain.ProductRepository;

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
