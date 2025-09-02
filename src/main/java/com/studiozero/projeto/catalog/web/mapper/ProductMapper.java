package com.studiozero.projeto.catalog.web.mapper;

import com.studiozero.projeto.catalog.domain.Product;
import com.studiozero.projeto.catalog.web.dto.ProductRequestDTO;
import com.studiozero.projeto.catalog.web.dto.ProductResponseDTO;

import java.util.List;

public class ProductMapper {
    public static Product toDomain(ProductRequestDTO dto) {
        if (dto == null) return null;
        return new Product(
            null,
            dto.getName(),
            dto.getQuantity(),
            dto.getClientValue(),
            dto.getInternalValue()
        );
    }

    public static Product toDomain(ProductRequestDTO dto, Integer id) {
        if (dto == null || id == null) return null;
        return new Product(
            id,
            dto.getName(),
            dto.getQuantity(),
            dto.getClientValue(),
            dto.getInternalValue()
        );
    }

    public static ProductResponseDTO toDTO(Product product) {
        if (product == null) return null;
        return new ProductResponseDTO(product);
    }

    public static List<ProductResponseDTO> toDTOList(List<Product> entities) {
        if (entities == null) return null;
        return entities.stream().map(ProductMapper::toDTO).toList();
    }
}
