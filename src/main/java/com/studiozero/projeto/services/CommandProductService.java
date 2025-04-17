package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.CommandProductRequestDTO;
import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.entities.CommandProduct;
import com.studiozero.projeto.entities.Product;
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
    private final CommandService commandService;

    public CommandProduct createCommandProduct(CommandProductRequestDTO dto) {
        Command command = commandRepository.findById(dto.getFkCommand())
                .orElseThrow(() -> new NotFoundException("Comanda não encontrada"));

        CommandProduct commandProduct = commandProductMapper.toEntity(dto);
        commandProduct.setCommand(command);

        Product product = productService.findProductById(commandProduct.getProduct().getId());
        product.setQuantity(product.getQuantity() - commandProduct.getProductQuantity());

        commandProduct.setProduct(product);

        productService.updateProduct(product);
        commandService.updateCommand(command);

        return commandProductRepository.save(commandProduct);
    }

    public CommandProduct findCommandProductById(Integer id) {
        return commandProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto da comanda não encontrado"));
    }

    public List<CommandProduct> listCommandProducts() {
        return commandProductRepository.findAll();
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

        commandService.updateCommand(current.getCommand());

        return commandProductRepository.save(updated);
    }

    public void deleteCommandProduct(Integer id) {
        CommandProduct commandProduct = commandProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto da comanda não encontrado"));

        Product product = commandProduct.getProduct();
        product.setQuantity(product.getQuantity() + commandProduct.getProductQuantity());
        productService.updateProduct(product);

        commandProductRepository.deleteById(id);
        commandService.updateCommand(commandProduct.getCommand());
    }
}
