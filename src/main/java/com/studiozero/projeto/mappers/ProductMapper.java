package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.ProductRequestDTO;
import com.studiozero.projeto.dtos.response.ProductResponseDTO;
import com.studiozero.projeto.entities.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setQuantity(dto.getQuantity());
        product.setClientValue(dto.getClientValue());
        product.setInternalValue(dto.getInternalValue());
        product.setTotalBuyValue(dto.getTotalBuyValue());
        return product;
    }

    public static ProductResponseDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setQuantity(product.getQuantity());
        dto.setClientValue(product.getClientValue());
        dto.setInternalValue(product.getInternalValue());
        return dto;
    }

    public static List<ProductResponseDTO> toListDtos(List<Product> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    public Product toEntity(ProductRequestDTO dto, Integer id) {
        if (dto == null) return null;

        return new Product(
                id,
                dto.getName(),
                dto.getQuantity(),
                dto.getClientValue(),
                dto.getInternalValue(),
                dto.getTotalBuyValue()
        );
    }
}
