package com.studiozero.projeto.infrastructure.mappers;

import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.infrastructure.entities.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductEntityMapper {
    public static Product toDomain(ProductEntity entity) {
        if (entity == null) return null;
        return new Product(
            entity.getId(),
            entity.getName(),
            entity.getQuantity(),
            entity.getClientValue(),
            entity.getInternalValue()
        );
    }

    public static ProductEntity toEntity(Product product) {
        if (product == null) return null;
        return new ProductEntity(
            product.getId(),
            product.getName(),
            product.getQuantity(),
            product.getClientValue(),
            product.getInternalValue()
        );
    }

    public static List<Product> toDomainList(List<ProductEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(ProductEntityMapper::toDomain).toList();
    }

    public static List<ProductEntity> toEntityList(List<Product> products) {
        if (products == null) return null;
        return products.stream().map(ProductEntityMapper::toEntity).toList();
    }
}
