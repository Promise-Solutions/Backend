package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.infrastructure.entities.CommandProductEntity;
import com.studiozero.projeto.infrastructure.mappers.CommandProductEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.Implements.CommandProductRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaCommandProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommandProductRepositoryTest {

    private JpaCommandProductRepository jpaCommandProductRepository;
    private CommandProductRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        jpaCommandProductRepository = mock(JpaCommandProductRepository.class);
        repository = new CommandProductRepositoryImpl(jpaCommandProductRepository);
    }

    @Test
    void testFindAllByCommand_Id() {
        Integer commandId = 1;
        CommandProductEntity entity = mock(CommandProductEntity.class);
        CommandProduct commandProduct = mock(CommandProduct.class);
        Command command = mock(Command.class);

        when(jpaCommandProductRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<CommandProductEntityMapper> mocked = mockStatic(CommandProductEntityMapper.class)) {
            mocked.when(() -> CommandProductEntityMapper.toDomain(entity)).thenReturn(commandProduct);
            when(commandProduct.getCommand()).thenReturn(command);
            when(command.getId()).thenReturn(commandId);

            List<CommandProduct> result = repository.findAllByCommand_Id(commandId);
            assertEquals(1, result.size());
            assertEquals(commandProduct, result.get(0));
        }
    }

    @Test
    void testFindAll() {
        CommandProductEntity entity = mock(CommandProductEntity.class);
        CommandProduct commandProduct = mock(CommandProduct.class);

        when(jpaCommandProductRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<CommandProductEntityMapper> mocked = mockStatic(CommandProductEntityMapper.class)) {
            mocked.when(() -> CommandProductEntityMapper.toDomain(entity)).thenReturn(commandProduct);

            List<CommandProduct> result = repository.findAll();
            assertEquals(1, result.size());
            assertEquals(commandProduct, result.get(0));
        }
    }

    @Test
    void testExistsByProduct_IdAndCommand_Id() {
        Integer productId = 2;
        Integer commandId = 3;
        CommandProductEntity entity = mock(CommandProductEntity.class);
        CommandProduct commandProduct = mock(CommandProduct.class);
        Product product = mock(Product.class);
        Command command = mock(Command.class);

        when(jpaCommandProductRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<CommandProductEntityMapper> mocked = mockStatic(CommandProductEntityMapper.class)) {
            mocked.when(() -> CommandProductEntityMapper.toDomain(entity)).thenReturn(commandProduct);
            when(commandProduct.getProduct()).thenReturn(product);
            when(product.getId()).thenReturn(productId);
            when(commandProduct.getCommand()).thenReturn(command);
            when(command.getId()).thenReturn(commandId);

            boolean exists = repository.existsByProduct_IdAndCommand_Id(productId, commandId);
            assertTrue(exists);
        }
    }

    @Test
    void testFindByProduct_IdAndCommand_IdFound() {
        Integer productId = 2;
        Integer commandId = 3;
        CommandProductEntity entity = mock(CommandProductEntity.class);
        CommandProduct commandProduct = mock(CommandProduct.class);
        Product product = mock(Product.class);
        Command command = mock(Command.class);

        when(jpaCommandProductRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<CommandProductEntityMapper> mocked = mockStatic(CommandProductEntityMapper.class)) {
            mocked.when(() -> CommandProductEntityMapper.toDomain(entity)).thenReturn(commandProduct);
            when(commandProduct.getProduct()).thenReturn(product);
            when(product.getId()).thenReturn(productId);
            when(commandProduct.getCommand()).thenReturn(command);
            when(command.getId()).thenReturn(commandId);

            CommandProduct result = repository.findByProduct_IdAndCommand_Id(productId, commandId);
            assertEquals(commandProduct, result);
        }
    }

    @Test
    void testFindByProduct_IdAndCommand_IdNotFound() {
        Integer productId = 2;
        Integer commandId = 3;
        CommandProductEntity entity = mock(CommandProductEntity.class);
        CommandProduct commandProduct = mock(CommandProduct.class);

        when(jpaCommandProductRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<CommandProductEntityMapper> mocked = mockStatic(CommandProductEntityMapper.class)) {
            mocked.when(() -> CommandProductEntityMapper.toDomain(entity)).thenReturn(commandProduct);
            when(commandProduct.getProduct()).thenReturn(null);

            CommandProduct result = repository.findByProduct_IdAndCommand_Id(productId, commandId);
            assertNull(result);
        }
    }

    @Test
    void testFindByIdFound() {
        Integer id = 10;
        CommandProductEntity entity = mock(CommandProductEntity.class);
        CommandProduct commandProduct = mock(CommandProduct.class);

        when(jpaCommandProductRepository.findById(id)).thenReturn(Optional.of(entity));
        try (MockedStatic<CommandProductEntityMapper> mocked = mockStatic(CommandProductEntityMapper.class)) {
            mocked.when(() -> CommandProductEntityMapper.toDomain(entity)).thenReturn(commandProduct);

            CommandProduct result = repository.findById(id);
            assertEquals(commandProduct, result);
        }
    }

    @Test
    void testFindByIdNotFound() {
        Integer id = 10;
        when(jpaCommandProductRepository.findById(id)).thenReturn(Optional.empty());

        CommandProduct result = repository.findById(id);
        assertNull(result);
    }

    @Test
    void testSave() {
        CommandProduct commandProduct = mock(CommandProduct.class);
        CommandProductEntity entity = mock(CommandProductEntity.class);

        try (MockedStatic<CommandProductEntityMapper> mocked = mockStatic(CommandProductEntityMapper.class)) {
            mocked.when(() -> CommandProductEntityMapper.toEntity(commandProduct)).thenReturn(entity);

            repository.save(commandProduct);
            verify(jpaCommandProductRepository).save(entity);
        }
    }

    @Test
    void testDeleteById() {
        Integer id = 5;
        repository.deleteById(id);
        verify(jpaCommandProductRepository).deleteById(id);
    }
}