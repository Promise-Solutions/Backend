package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.entities.CommandProduct;
import com.studiozero.projeto.entities.Product;
import com.studiozero.projeto.enums.Context;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.mappers.CommandProductMapper;
import com.studiozero.projeto.repositories.CommandProductRepository;
import com.studiozero.projeto.repositories.CommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandProductServiceTest {

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

    private Command command;
    private Product product;
    private CommandProduct commandProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        command = new Command();
        command.setId(1);
        command.setStatus(Status.OPEN);

        product = new Product();
        product.setId(1);
        product.setQuantity(50);
        product.setClientValue(10.0);
        product.setInternalValue(5.0);

        commandProduct = new CommandProduct();
        commandProduct.setId(1);
        commandProduct.setCommand(command);
        commandProduct.setProduct(product);
        commandProduct.setProductQuantity(2);
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
        command.setIsInternal(false);
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
}
