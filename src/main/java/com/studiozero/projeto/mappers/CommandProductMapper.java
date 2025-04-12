package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.CommandProductRequestDTO;
import com.studiozero.projeto.dtos.response.ClientResponseDTO;
import com.studiozero.projeto.dtos.response.CommandProductResponseDTO;
import com.studiozero.projeto.entities.CommandProduct;
import org.springframework.stereotype.Component;

@Component
public class CommandProductMapper {
    public CommandProduct toEntity(CommandProductRequestDTO dto) {
        CommandProduct commandProduct = new CommandProduct();
        commandProduct.setFkCommand(dto.getFkCommand());
        commandProduct.setFkProduct(dto.getFkProduct());
        commandProduct.setProductQuantity(dto.getProductQuantity());
        commandProduct.setUnitValue(dto.getUnitValue());
        return commandProduct;
    }

    public CommandProductResponseDTO toDTO(CommandProduct commandProduct) {
        return new CommandProductResponseDTO(commandProduct);
    }
}
