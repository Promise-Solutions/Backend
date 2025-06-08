package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.CommandRequestDTO;
import com.studiozero.projeto.entities.*;
import com.studiozero.projeto.enums.Context;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.exceptions.UnauthorizedException;
import com.studiozero.projeto.mappers.CommandMapper;
import com.studiozero.projeto.repositories.*;

import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandServiceTest {

    @InjectMocks
    private CommandService commandService;

    @Mock
    private CommandRepository commandRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CommandMapper commandMapper;

    @Mock
    private CommandProductRepository commandProductRepository;

    @Mock
    private TracingService tracingService;

    private Command command;
    private Employee employee;
    private Client client;
    private CommandRequestDTO commandDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employee = new Employee();
        employee.setId(UUID.randomUUID());

        client = new Client();
        client.setId(UUID.randomUUID());

        command = new Command();
        command.setId(1);
        command.setStatus(Status.OPEN);
        command.setEmployee(employee);

        commandDTO = new CommandRequestDTO();
        commandDTO.setFkEmployee(employee.getId());
        commandDTO.setFkClient(client.getId());
    }

    @Test
    @DisplayName("Should create command successfully")
    void createCommand_Success() {
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        when(clientRepository.findById(any())).thenReturn(Optional.of(client));
        when(commandMapper.toEntity(any())).thenReturn(command);
        when(commandRepository.save(any())).thenReturn(command);

        Command created = commandService.createCommand(commandDTO);

        assertNotNull(created);
        verify(tracingService).setTracing(Context.BAR);
        verify(commandRepository).save(command);
    }

    @Test
    @DisplayName("Should throw NotFoundException when employee not found")
    void createCommand_EmployeeNotFound() {
        when(employeeRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> commandService.createCommand(commandDTO));
        verify(commandRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should find command by ID")
    void findCommandById_Success() {
        when(commandRepository.findById(1)).thenReturn(Optional.of(command));

        Command found = commandService.findCommandById(1);

        assertEquals(command, found);
    }

    @Test
    @DisplayName("Should throw NotFoundException when command not found")
    void findCommandById_NotFound() {
        when(commandRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> commandService.findCommandById(1));
    }

    @Test
    @DisplayName("Should list all commands")
    void listCommands_All() {
        List<Command> list = List.of(command, command);
        when(commandRepository.findAll()).thenReturn(list);

        List<Command> result = commandService.listCommands();

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should list commands by status")
    void listCommands_ByStatus() {
        List<Command> list = List.of(command);
        when(commandRepository.findAllByStatus(Status.OPEN)).thenReturn(list);

        List<Command> result = commandService.listCommands(Status.OPEN);

        assertEquals(1, result.size());
        assertEquals(Status.OPEN, result.get(0).getStatus());
    }

    @Test
    @DisplayName("Should update command successfully")
    void updateCommand_Success() {
        command.setStatus(Status.CLOSED);
        command.setDiscount(10.0);
        command.setId(1);

        CommandProduct cp = new CommandProduct();
        cp.setUnitValue(10.0);
        cp.setProductQuantity(2);

        when(commandRepository.existsById(1)).thenReturn(true);
        when(commandProductRepository.findAllByCommand_Id(1)).thenReturn(List.of(cp));
        when(commandRepository.save(any())).thenReturn(command);

        Command updated = commandService.updateCommand(command);

        assertEquals(18.0, updated.getTotalValue());
        verify(tracingService).setTracing(Context.BAR);
    }

    @Test
    @DisplayName("Should throw UnauthorizedException if discount applied to non-closed command")
    void updateCommand_DiscountOnOpenCommand() {
        command.setStatus(Status.OPEN);
        command.setDiscount(10.0);
        command.setId(1);

        when(commandRepository.existsById(1)).thenReturn(true);
        when(commandProductRepository.findAllByCommand_Id(1)).thenReturn(List.of());

        assertThrows(UnauthorizedException.class, () -> commandService.updateCommand(command));
    }

    @Test
    @DisplayName("Should throw NotFoundException when updating non-existent command")
    void updateCommand_NotFound() {
        when(commandRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> commandService.updateCommand(command));
    }

    @Test
    @DisplayName("Should delete command successfully when closed")
    void deleteCommand_Success() {
        command.setStatus(Status.CLOSED);
        when(commandRepository.findById(1)).thenReturn(Optional.of(command));

        commandService.deleteCommand(1);

        verify(tracingService).setTracing(Context.BAR);
        verify(commandRepository).deleteById(1);
    }

    @Test
    @DisplayName("Should throw UnauthorizedException when deleting open command")
    void deleteCommand_NotClosed() {
        command.setStatus(Status.OPEN);
        when(commandRepository.findById(1)).thenReturn(Optional.of(command));

        assertThrows(UnauthorizedException.class, () -> commandService.deleteCommand(1));
        verify(commandRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("Should throw NotFoundException when deleting non-existent command")
    void deleteCommand_NotFound() {
        when(commandRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> commandService.deleteCommand(1));
    }
}
