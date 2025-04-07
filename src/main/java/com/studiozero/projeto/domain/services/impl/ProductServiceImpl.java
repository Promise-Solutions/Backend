package com.studiozero.projeto.domain.services.impl;

import com.studiozero.projeto.applications.web.dtos.request.*;
import com.studiozero.projeto.applications.web.dtos.response.ProductResponseDTO;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.domain.exceptions.EntityNotFoundException;
import com.studiozero.projeto.domain.repositories.ProductRepository;
import com.studiozero.projeto.domain.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponseDTO save(ProductCreateRequestDTO productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        product.setUnitValue(productDto.getUnitValue());

        Product savedProduct = productRepository.save(product);

        return new ProductResponseDTO(savedProduct);
    }

    @Override
    public List<ProductResponseDTO> list() {
            List<Product> products = productRepository.findAll();
            return products.stream()
                    .map(ProductResponseDTO::new)
                    .toList();
        }

    @Override
    public ProductResponseDTO findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return new ProductResponseDTO(product.get());
    }

    @Override
    public ProductResponseDTO update(ProductUpdateRequestDTO productDto) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        product.setUnitValue(productDto.getUnitValue());

        Product updatedProduct = productRepository.save(product);

        return new ProductResponseDTO(updatedProduct);
    }

    @Override
    public String delete(ProductDeleteRequestDTO productDto) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        productRepository.delete(product);
        return "Product deleted successfully";
    }
}
