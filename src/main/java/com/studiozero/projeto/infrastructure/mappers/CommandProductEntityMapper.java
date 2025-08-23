package com.studiozero.projeto.infrastructure.mappers;

import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.infrastructure.entities.CommandProductEntity;
import com.studiozero.projeto.infrastructure.entities.CommandEntity;
import com.studiozero.projeto.infrastructure.entities.ProductEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommandProductEntityMapper {
    public static CommandProductEntity toEntity(CommandProduct commandProduct) {
        if (commandProduct == null) return null;
        CommandEntity commandEntity = null;
        ProductEntity productEntity = null;
        if (commandProduct.getCommand() != null) {
            commandEntity = new CommandEntity();
            commandEntity.setId(commandProduct.getCommand().getId());
        }
        if (commandProduct.getProduct() != null) {
            productEntity = new ProductEntity();
            productEntity.setId(commandProduct.getProduct().getId());
        }
        return new CommandProductEntity(
            commandProduct.getId(),
            productEntity,
            commandEntity,
            commandProduct.getProductQuantity(),
            commandProduct.getUnitValue()
        );
    }

    public static CommandProduct toDomain(CommandProductEntity entity) {
        if (entity == null) return null;
        return new CommandProduct(
            entity.getId(),
            ProductEntityMapper.toDomain(entity.getProduct()),
            CommandEntityMapper.toDomain(entity.getCommand()),
            entity.getProductQuantity(),
            entity.getUnitValue()
        );
    }

    public static List<CommandProduct> toDomainList(List<CommandProductEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(CommandProductEntityMapper::toDomain).toList();
    }

    public static List<CommandProductEntity> toEntityList(List<CommandProduct> commandProducts) {
        if (commandProducts == null) return null;
        return commandProducts.stream().map(CommandProductEntityMapper::toEntity).toList();
    }
}
