package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.web.dtos.request.CommandProductRequestDTO;
import com.studiozero.projeto.web.dtos.response.CommandProductResponseDTO;
import com.studiozero.projeto.domain.repositories.CommandRepository;
import com.studiozero.projeto.domain.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CommandProductMapper {
    private final CommandRepository commandRepository;
    private final ProductRepository productRepository;

    public CommandProductMapper(CommandRepository commandRepository, ProductRepository productRepository) {
        this.commandRepository = commandRepository;
        this.productRepository = productRepository;
    }

    public CommandProduct toEntity(CommandProductRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Command command = commandRepository.findById(dto.getFkCommand());
        Product product = productRepository.findById(dto.getFkProduct());
        if (command == null || product == null) {
            return null;
        }
        return new CommandProduct(
                null, // id será gerado pelo banco ou use case
                product,
                command,
                dto.getProductQuantity(),
                dto.getUnitValue());
    }

    public static CommandProductResponseDTO toDTO(CommandProduct commandProduct) {
        if (commandProduct == null) {
            return null;
        }
        CommandProductResponseDTO dto = new CommandProductResponseDTO();
        dto.setId(commandProduct.getId());
        dto.setProductQuantity(commandProduct.getProductQuantity());
        dto.setUnitValue(commandProduct.getUnitValue());
        double total = commandProduct.getUnitValue() * commandProduct.getProductQuantity();
        dto.setTotalValue(total);
        dto.setFkCommand(commandProduct.getCommand() != null ? commandProduct.getCommand().getId() : null);
        dto.setFkProduct(commandProduct.getProduct() != null ? commandProduct.getProduct().getId() : null);
        return dto;
    }

    public static List<CommandProductResponseDTO> toListDtos(List<CommandProduct> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(CommandProductMapper::toDTO)
                .toList();
    }

    public CommandProduct toEntity(CommandProductRequestDTO dto, Integer id) {
        if (dto == null || id == null) {
            return null;
        }
        Command command = commandRepository.findById(dto.getFkCommand());
        Product product = productRepository.findById(dto.getFkProduct());
        if (command == null || product == null) {
            return null;
        }
        return new CommandProduct(
                id,
                product,
                command,
                dto.getProductQuantity(),
                dto.getUnitValue());
    }
}
