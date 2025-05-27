package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Product;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
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
            validateProductsValues(product);
            return productRepository.save(product);
        }
        throw new NotFoundException("Product not found");
    }

    public void deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
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
