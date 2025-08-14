package com.studiozero.projeto.services;

import com.studiozero.projeto.application.services.CommandProductService;
import com.studiozero.projeto.application.services.ProductService;
import com.studiozero.projeto.application.services.TracingService;
import com.studiozero.projeto.application.services.dtos.request.CommandProductRequestDTO;
import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.application.services.enums.Context;
import com.studiozero.projeto.application.services.enums.Status;
import com.studiozero.projeto.infrastructure.configs.exceptions.NotFoundException;
import com.studiozero.projeto.web.controllers.mappers.CommandProductMapper;
import com.studiozero.projeto.domain.entities.repositories.CommandProductRepository;
import com.studiozero.projeto.domain.entities.repositories.CommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommandProductServiceTest {

    @InjectMocks
    private CommandProductService commandProductService;

    @Mock
    private CommandProductRepository commandProductRepository;

    @Mock
    private CommandRepository commandRepository;

    @Mock
    private CommandProductMapper commandProductMapper;

    @Mock
    private ProductService productService;

    @Mock
    private TracingService tracingService;

    private CommandProduct existingCommandProduct;
    private CommandProduct updatedCommandProduct;
    private Product oldProduct;
    private Product newProduct;
    private Command command;
    private Product product;
    private CommandProduct commandProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup command once
        command = new Command();
        command.setId(1);
        command.setStatus(Status.OPEN);
        command.setIsInternal(false);
        command.setDiscount(0.0);

        // Setup product
        product = new Product();
        product.setId(1);
        product.setQuantity(50);
        product.setClientValue(10.0);
        product.setInternalValue(5.0);

        // Setup commandProduct
        commandProduct = new CommandProduct();
        commandProduct.setId(1);
        commandProduct.setCommand(command);
        commandProduct.setProduct(product);
        commandProduct.setProductQuantity(2);

        // Old product for update tests
        oldProduct = new Product();
        oldProduct.setId(10);
        oldProduct.setQuantity(100);
        oldProduct.setClientValue(10.0);
        oldProduct.setInternalValue(8.0);

        // New product for update tests
        newProduct = new Product();
        newProduct.setId(20);
        newProduct.setQuantity(50);
        newProduct.setClientValue(20.0);
        newProduct.setInternalValue(15.0);

        // Existing CommandProduct
        existingCommandProduct = new CommandProduct();
        existingCommandProduct.setId(1);
        existingCommandProduct.setProduct(oldProduct);
        existingCommandProduct.setProductQuantity(5);
        existingCommandProduct.setCommand(command);

        // Updated CommandProduct placeholder
        updatedCommandProduct = new CommandProduct();
        updatedCommandProduct.setId(1);
        updatedCommandProduct.setCommand(command);
    }

    @Test
    @DisplayName("Should find CommandProduct by ID successfully")
    void findCommandProductById_Success() {
        when(commandProductRepository.findById(1)).thenReturn(Optional.of(commandProduct));

        CommandProduct result = commandProductService.findCommandProductById(1);

        assertEquals(commandProduct, result);
    }

    @Test
    @DisplayName("Should throw NotFoundException when CommandProduct not found by ID")
    void findCommandProductById_NotFound() {
        when(commandProductRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> commandProductService.findCommandProductById(1));
    }

    @Test
    @DisplayName("Should list all CommandProducts")
    void listCommandProducts_All() {
        List<CommandProduct> list = List.of(commandProduct, commandProduct);
        when(commandProductRepository.findAll()).thenReturn(list);

        List<CommandProduct> result = commandProductService.listCommandProducts();

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should list CommandProducts by command ID")
    void listCommandProducts_ByCommandId() {
        when(commandProductRepository.findAllByCommand_Id(1)).thenReturn(List.of(commandProduct));

        List<CommandProduct> result = commandProductService.listCommandProducts(1);

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should update total command value successfully")
    void updateCommand_Success() {
        when(commandRepository.existsById(1)).thenReturn(true);
        when(commandProductRepository.findAllByCommand_Id(1)).thenReturn(List.of(commandProduct));
        when(commandRepository.save(any(Command.class))).thenAnswer(inv -> inv.getArgument(0));

        Command result = commandProductService.updateCommand(command);

        assertEquals(20.0, result.getTotalValue());
        verify(tracingService).setTracing(Context.BAR);
    }

    @Test
    @DisplayName("Should throw NotFoundException when trying to update non-existent command")
    void updateCommand_NotFound() {
        when(commandRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> commandProductService.updateCommand(command));
    }

    @Test
    @DisplayName("Should delete CommandProduct successfully and update stock")
    void deleteCommandProduct_Success() {
        when(commandProductRepository.findById(1)).thenReturn(Optional.of(commandProduct));
        when(commandProductRepository.findAllByCommand_Id(1)).thenReturn(List.of());
        when(commandRepository.existsById(1)).thenReturn(true);
        when(commandRepository.save(any())).thenReturn(command);

        commandProductService.deleteCommandProduct(1);

        verify(productService).updateProduct(any(Product.class));
        verify(commandProductRepository).deleteById(1);
    }

    @Test
    @DisplayName("Should throw NotFoundException when trying to delete non-existent CommandProduct")
    void deleteCommandProduct_NotFound() {
        when(commandProductRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> commandProductService.deleteCommandProduct(1));
    }

    @Test
    @DisplayName("Should apply discount when command status is CLOSED")
    void updateCommand_ApplyDiscountWhenClosed() {
        command.setStatus(Status.CLOSED);
        command.setDiscount(10.0);

        when(commandRepository.existsById(1)).thenReturn(true);
        when(commandProductRepository.findAllByCommand_Id(1)).thenReturn(List.of(commandProduct));
        when(commandRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Command updated = commandProductService.updateCommand(command);

        double expected = 20.0 - 2.0;
        assertEquals(expected, updated.getTotalValue());
        verify(tracingService).setTracing(Context.BAR);
    }

    @Test
    @DisplayName("Should throw NotFoundException when Command is not found")
    void createCommandProduct_shouldThrowNotFoundException_whenCommandNotFound() {
        Integer commandId = 1;
        CommandProductRequestDTO dto = new CommandProductRequestDTO();
        dto.setFkCommand(commandId);

        when(commandRepository.findById(commandId)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> {
            commandProductService.createCommandProduct(dto);
        });

        assertEquals("Comanda não encontrada", ex.getMessage());
        verify(commandProductRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw NotFoundException when CommandProduct does not exist")
    void testUpdateCommandProduct_NotFound() {
        Integer id = 1;
        when(commandProductRepository.existsById(id)).thenReturn(false);

        updatedCommandProduct.setId(id);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            commandProductService.updateCommandProduct(updatedCommandProduct);
        });

        assertEquals("Produto da comanda não encontrado", exception.getMessage());
    }
}
