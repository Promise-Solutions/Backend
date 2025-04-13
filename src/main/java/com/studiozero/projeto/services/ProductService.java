package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.ProductRequestDTO;
import com.studiozero.projeto.dtos.response.ProductResponseDTO;
import com.studiozero.projeto.entities.Product;
import com.studiozero.projeto.exceptions.EntityNotFoundException;
import com.studiozero.projeto.mappers.ProductMapper;
import com.studiozero.projeto.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductResponseDTO save(ProductRequestDTO productDto) {
        Product product = productMapper.toEntity(productDto);
        Product savedProduct = productRepository.save(product);

        return productMapper.toDTO(savedProduct);
    }
    public ProductResponseDTO findById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productMapper.toDTO(product);
    }

    public List<ProductResponseDTO> findAll() {
            return productRepository.findAll().stream()
                    .map(productMapper::toDTO)
                    .toList();
        }


    public ProductResponseDTO update(Integer id, ProductRequestDTO productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        product.setUnitValue(productDto.getUnitValue());

        Product updatedProduct = productRepository.save(product);

        return productMapper.toDTO(updatedProduct);
    }

    public void delete(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        productRepository.delete(product);
    }
}
