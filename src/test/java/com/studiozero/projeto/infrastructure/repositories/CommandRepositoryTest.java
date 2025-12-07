package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.infrastructure.entities.CommandEntity;
import com.studiozero.projeto.infrastructure.mappers.CommandEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.Implements.CommandRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaCommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommandRepositoryTest {

    private JpaCommandRepository jpaCommandRepository;
    private CommandRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        jpaCommandRepository = mock(JpaCommandRepository.class);
        repository = new CommandRepositoryImpl(jpaCommandRepository);
    }

    @Test
    void testFindAll() {
        CommandEntity entity = mock(CommandEntity.class);
        Command command = mock(Command.class);

        when(jpaCommandRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<CommandEntityMapper> mocked = mockStatic(CommandEntityMapper.class)) {
            mocked.when(() -> CommandEntityMapper.toDomain(entity)).thenReturn(command);

            List<Command> result = repository.findAll();
            assertEquals(1, result.size());
            assertEquals(command, result.get(0));
        }
    }

    @Test
    void testExistsByCommandNumber() {
        Integer commandNumber = 123;
        CommandEntity entity = mock(CommandEntity.class);

        when(jpaCommandRepository.findAll()).thenReturn(List.of(entity));
        when(entity.getCommandNumber()).thenReturn(commandNumber);

        assertTrue(repository.existsByCommandNumber(commandNumber));
    }

    @Test
    void testFindAllByStatus() {
        Status status = Status.CLOSED;
        CommandEntity entity = mock(CommandEntity.class);
        Command command = mock(Command.class);

        when(jpaCommandRepository.findAllByStatus(status)).thenReturn(List.of(entity));
        try (MockedStatic<CommandEntityMapper> mocked = mockStatic(CommandEntityMapper.class)) {
            mocked.when(() -> CommandEntityMapper.toDomain(entity)).thenReturn(command);

            List<Command> result = repository.findAllByStatus(status);
            assertEquals(1, result.size());
            assertEquals(command, result.get(0));
        }
    }

    @Test
    void testFindAllByClientIdAndStatus() {
        UUID clientId = UUID.randomUUID();
        Status status = Status.CLOSED;
        CommandEntity entity = mock(CommandEntity.class);
        Command command = mock(Command.class);

        when(jpaCommandRepository.findAllByClientIdAndStatus(clientId, status)).thenReturn(List.of(entity));
        try (MockedStatic<CommandEntityMapper> mocked = mockStatic(CommandEntityMapper.class)) {
            mocked.when(() -> CommandEntityMapper.toDomain(entity)).thenReturn(command);

            List<Command> result = repository.findAllByClientIdAndStatus(clientId, status);
            assertEquals(1, result.size());
            assertEquals(command, result.get(0));
        }
    }

    @Test
    void testFindMaxOpeningDate() {
        CommandEntity entity = mock(CommandEntity.class);
        Command command = mock(Command.class);
        LocalDateTime date = LocalDateTime.now();

        when(jpaCommandRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<CommandEntityMapper> mocked = mockStatic(CommandEntityMapper.class)) {
            mocked.when(() -> CommandEntityMapper.toDomain(entity)).thenReturn(command);
            when(command.getOpeningDateTime()).thenReturn(date);

            LocalDateTime result = repository.findMaxOpeningDate();
            assertEquals(date, result);
        }
    }

    @Test
    void testFindMaxClosingDate() {
        CommandEntity entity = mock(CommandEntity.class);
        Command command = mock(Command.class);
        LocalDateTime date = LocalDateTime.now();

        when(jpaCommandRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<CommandEntityMapper> mocked = mockStatic(CommandEntityMapper.class)) {
            mocked.when(() -> CommandEntityMapper.toDomain(entity)).thenReturn(command);
            when(command.getClosingDateTime()).thenReturn(date);

            LocalDateTime result = repository.findMaxClosingDate();
            assertEquals(date, result);
        }
    }

    @Test
    void testFindByIdFound() {
        Integer id = 10;
        CommandEntity entity = mock(CommandEntity.class);
        Command command = mock(Command.class);

        when(jpaCommandRepository.findById(id)).thenReturn(Optional.of(entity));
        try (MockedStatic<CommandEntityMapper> mocked = mockStatic(CommandEntityMapper.class)) {
            mocked.when(() -> CommandEntityMapper.toDomain(entity)).thenReturn(command);

            Command result = repository.findById(id);
            assertEquals(command, result);
        }
    }

    @Test
    void testFindByIdNotFound() {
        Integer id = 10;
        when(jpaCommandRepository.findById(id)).thenReturn(Optional.empty());

        Command result = repository.findById(id);
        assertNull(result);
    }

    @Test
    void testSave() {
        Command command = mock(Command.class);
        CommandEntity entity = mock(CommandEntity.class);

        try (MockedStatic<CommandEntityMapper> mocked = mockStatic(CommandEntityMapper.class)) {
            mocked.when(() -> CommandEntityMapper.toEntity(command)).thenReturn(entity);

            repository.save(command);
            verify(jpaCommandRepository).save(entity);
        }
    }

    @Test
    void testDeleteById() {
        Integer id = 5;
        repository.deleteById(id);
        verify(jpaCommandRepository).deleteById(id);
    }
}