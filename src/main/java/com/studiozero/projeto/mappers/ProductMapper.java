package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.ProductRequestDTO;
import com.studiozero.projeto.dtos.response.ProductResponseDTO;
import com.studiozero.projeto.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setQuantity(dto.getQuantity());
        product.setUnitValue(dto.getUnitValue());
        return product;
    }

    public ProductResponseDTO toDTO(Product product) {
        return new ProductResponseDTO(product);
    }
}
