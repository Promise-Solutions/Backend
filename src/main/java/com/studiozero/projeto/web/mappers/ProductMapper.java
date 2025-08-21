package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.web.dtos.request.ProductRequestDTO;
import com.studiozero.projeto.web.dtos.response.ProductResponseDTO;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO dto) {
        if (dto == null)
            return null;
        // id deve ser null para novo Product (banco gera)
        return new Product(
                null,
                dto.getName(),
                dto.getQuantity(),
                dto.getClientValue(),
                dto.getInternalValue(),
                null // totalBuyValue pode ser calculado depois
        );
    }

    public static ProductResponseDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductResponseDTO(product);
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
        if (dto == null || id == null)
            return null;
        return new Product(
                id,
                dto.getName(),
                dto.getQuantity(),
                dto.getClientValue(),
                dto.getInternalValue(),
                null // totalBuyValue pode ser calculado depois
        );
    }
}
