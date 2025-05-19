package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.CommandProductRequestDTO;
import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.entities.CommandProduct;
import com.studiozero.projeto.entities.Product;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.mappers.CommandProductMapper;
import com.studiozero.projeto.repositories.CommandProductRepository;
import com.studiozero.projeto.repositories.CommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandProductService {

    private final CommandProductRepository commandProductRepository;
    private final CommandRepository commandRepository;
    private final CommandProductMapper commandProductMapper;
    private final ProductService productService;

    public CommandProduct createCommandProduct(CommandProductRequestDTO dto) {
        Command command = commandRepository.findById(dto.getFkCommand())
                .orElseThrow(() -> new NotFoundException("Comanda não encontrada"));

        CommandProduct commandProduct = commandProductMapper.toEntity(dto);
        commandProduct.setCommand(command);

        Product product = productService.findProductById(commandProduct.getProduct().getId());
        product.setQuantity(product.getQuantity() - commandProduct.getProductQuantity());

        commandProduct.setProduct(product);

        productService.updateProduct(product);

        CommandProduct saved = commandProductRepository.save(commandProduct);
        updateCommand(command); // agora sim

        return saved;
    }

    public CommandProduct findCommandProductById(Integer id) {
        return commandProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto da comanda não encontrado"));
    }

    public List<CommandProduct> listCommandProducts() {
        return commandProductRepository.findAll();
    }

    public List<CommandProduct> listCommandProducts(Integer fkComanda) {
        return commandProductRepository.findAllByCommand_Id(fkComanda);
    }

    public CommandProduct updateCommandProduct(CommandProduct updated) {
        Integer id = updated.getId();

        if (!commandProductRepository.existsById(id)) {
            throw new NotFoundException("Produto da comanda não encontrado");
        }

        CommandProduct current = findCommandProductById(id);
        Product oldProduct = current.getProduct();
        Product newProduct = updated.getProduct();

        int oldQty = current.getProductQuantity();
        int newQty = updated.getProductQuantity();

        if (!oldProduct.getId().equals(newProduct.getId())) {
            oldProduct.setQuantity(oldProduct.getQuantity() + oldQty);
            newProduct.setQuantity(newProduct.getQuantity() - newQty);

            productService.updateProduct(oldProduct);
            productService.updateProduct(newProduct);
        } else {
            int qtyDiff = newQty - oldQty;
            newProduct.setQuantity(newProduct.getQuantity() - qtyDiff);
            productService.updateProduct(newProduct);
        }

        updated.setCommand(current.getCommand());
        updated.setProduct(newProduct);

        CommandProduct saved = commandProductRepository.save(updated);
        updateCommand(saved.getCommand());

        return saved;
    }

    public void deleteCommandProduct(Integer id) {
        CommandProduct commandProduct = commandProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto da comanda não encontrado"));

        Product product = commandProduct.getProduct();

        Command command = commandProduct.getCommand();

        if (command.getStatus() != Status.CLOSED) {
            product.setQuantity(product.getQuantity() + commandProduct.getProductQuantity());
        }

        productService.updateProduct(product);

        commandProductRepository.deleteById(id);
        updateCommand(commandProduct.getCommand());
    }

    public Command updateCommand(Command command) {
        if (!commandRepository.existsById(command.getId())) {
            throw new NotFoundException("Comanda não encontrada");
        }

        List<CommandProduct> commandProducts = commandProductRepository.findAllByCommand_Id(command.getId());

        Double totalValue = commandProducts.stream()
                .mapToDouble(cp -> {
                    Product product = cp.getProduct();
                    double unitValue = Boolean.TRUE.equals(command.getIsIntenal())
                            ? product.getInternalValue()
                            : product.getClientValue();
                    return unitValue * cp.getProductQuantity();
                })
                .sum();

        if (command.getStatus() == Status.CLOSED && Double.compare(command.getDiscount(), 0.0) != 0) {
            double discount = (totalValue * command.getDiscount()) / 100;
            totalValue -= discount;
        }

        command.setTotalValue(totalValue);
        return commandRepository.save(command);
    }
}
