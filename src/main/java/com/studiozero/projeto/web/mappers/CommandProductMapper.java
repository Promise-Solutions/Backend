package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.web.dtos.request.CommandProductRequestDTO;
import com.studiozero.projeto.web.dtos.response.CommandProductResponseDTO;
import java.util.List;

public class CommandProductMapper {
    public static CommandProduct toDomain(CommandProductRequestDTO dto, Product product, Command command) {
        if (dto == null || product == null || command == null) return null;
        return new CommandProduct(
            null,
            product,
            command,
            dto.getProductQuantity(),
            dto.getUnitValue()
        );
    }

    public static CommandProduct toDomain(CommandProductRequestDTO dto, Integer id, Product product, Command command) {
        if (dto == null || product == null || command == null) return null;
        return new CommandProduct(
            id,
            product,
            command,
            dto.getProductQuantity(),
            dto.getUnitValue()
        );
    }

    public static CommandProductResponseDTO toDTO(CommandProduct commandProduct) {
        if (commandProduct == null) return null;
        CommandProductResponseDTO dto = new CommandProductResponseDTO();
        dto.setId(commandProduct.getId());
        dto.setProductQuantity(commandProduct.getProductQuantity());
        dto.setUnitValue(commandProduct.getUnitValue());
        dto.setFkProduct(commandProduct.getProduct() != null ? commandProduct.getProduct().getId() : null);
        dto.setFkCommand(commandProduct.getCommand() != null ? commandProduct.getCommand().getId() : null);
        return dto;
    }

    public static List<CommandProductResponseDTO> toDTOList(List<CommandProduct> entities) {
        if (entities == null) return null;
        return entities.stream().map(CommandProductMapper::toDTO).toList();
    }
}
