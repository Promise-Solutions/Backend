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
        return productRepository.save(product);
    }

    public Product findProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public List<Product> listProducts() {
        return productRepository.findAll();
    }


    public Product updateProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            product.setId(product.getId());
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
}
