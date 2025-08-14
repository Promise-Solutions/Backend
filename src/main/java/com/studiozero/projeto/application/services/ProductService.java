package com.studiozero.projeto.application.services;

import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.application.enums.Context;
import com.studiozero.projeto.infrastructure.exceptions.NotFoundException;
import com.studiozero.projeto.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final TracingService tracingService;

    public Product createProduct(Product product) {
        tracingService.setTracing(Context.BAR);
        validateProductsValues(product);
        product.setQuantity(0);
        return productRepository.save(product);
    }

    public Product findProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public List<Product> listProducts() {
        return productRepository.findAll();
    }


    //analisar a lógica de negócio para alteração de produto
    public Product updateProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            product.setId(product.getId());
            tracingService.setTracing(Context.BAR);
            validateProductsValues(product);
            return productRepository.save(product);
        }
        throw new NotFoundException("Product not found");
    }

    public void deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            tracingService.setTracing(Context.BAR);
            productRepository.deleteById(id);
        } else {
            throw new NotFoundException("Product not found");
        }
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
