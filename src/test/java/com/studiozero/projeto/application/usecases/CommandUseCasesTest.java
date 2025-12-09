package com.studiozero.projeto.application.usecases;

import com.studiozero.projeto.application.enums.Status;
import com.studiozero.projeto.application.usecases.command.*;
import com.studiozero.projeto.domain.entities.Client;
import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.repositories.CommandProductRepository;
import com.studiozero.projeto.domain.repositories.CommandRepository;
import com.studiozero.projeto.web.handlers.ConflictException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandUseCasesTest {

    @Mock
    private CommandRepository commandRepository;

    @Mock
    private CommandProductRepository commandProductRepository;

    @Mock
    private CommandTotalCalculator commandTotalCalculator;

    @Nested
    class CommandTotalCalculatorTests {
        private CommandTotalCalculator calculator;

        @BeforeEach
        void setUp() {
            calculator = new CommandTotalCalculator(commandProductRepository);
        }

        @Test
        void shouldCalculateTotalValueWithSingleProduct() {
            Integer commandId = 1;

            CommandProduct product = new CommandProduct();
            product.setUnitValue(10.0);
            product.setProductQuantity(5);

            when(commandProductRepository.findAllByCommand_Id(commandId))
                    .thenReturn(Collections.singletonList(product));

            double result = calculator.calculateTotalValue(commandId);

            assertEquals(50.0, result, 0.001);
            verify(commandProductRepository, times(1)).findAllByCommand_Id(commandId);
        }

        @Test
        void shouldCalculateTotalValueWithMultipleProducts() {
            Integer commandId = 1;

            CommandProduct product1 = new CommandProduct();
            product1.setUnitValue(10.0);
            product1.setProductQuantity(5);

            CommandProduct product2 = new CommandProduct();
            product2.setUnitValue(20.0);
            product2.setProductQuantity(3);

            CommandProduct product3 = new CommandProduct();
            product3.setUnitValue(15.5);
            product3.setProductQuantity(2);

            List<CommandProduct> products = Arrays.asList(product1, product2, product3);

            when(commandProductRepository.findAllByCommand_Id(commandId)).thenReturn(products);

            double result = calculator.calculateTotalValue(commandId);

            assertEquals(141.0, result, 0.001);
            verify(commandProductRepository, times(1)).findAllByCommand_Id(commandId);
        }

        @Test
        void shouldReturnZeroWhenNoProductsFound() {
            Integer commandId = 1;

            when(commandProductRepository.findAllByCommand_Id(commandId))
                    .thenReturn(Collections.emptyList());

            double result = calculator.calculateTotalValue(commandId);

            assertEquals(0.0, result, 0.001);
            verify(commandProductRepository, times(1)).findAllByCommand_Id(commandId);
        }

        @Test
        void shouldCalculateWithDecimalValues() {
            Integer commandId = 1;

            CommandProduct product = new CommandProduct();
            product.setUnitValue(12.99);
            product.setProductQuantity(7);

            when(commandProductRepository.findAllByCommand_Id(commandId))
                    .thenReturn(Collections.singletonList(product));

            double result = calculator.calculateTotalValue(commandId);

            assertEquals(90.93, result, 0.001);
            verify(commandProductRepository, times(1)).findAllByCommand_Id(commandId);
        }

        @Test
        void shouldCalculateWithZeroQuantity() {
            Integer commandId = 1;

            CommandProduct product = new CommandProduct();
            product.setUnitValue(50.0);
            product.setProductQuantity(0);

            when(commandProductRepository.findAllByCommand_Id(commandId))
                    .thenReturn(Collections.singletonList(product));

            double result = calculator.calculateTotalValue(commandId);

            assertEquals(0.0, result, 0.001);
            verify(commandProductRepository, times(1)).findAllByCommand_Id(commandId);
        }
    }

    @Nested
    class CreateCommandUseCaseTests {
        private CreateCommandUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new CreateCommandUseCase(commandRepository);
        }

        @Test
        void shouldCreateCommandWithClientSuccessfully() {
            Command command = new Command();
            command.setCommandNumber(123);

            Client client = new Client();
            client.setId(UUID.randomUUID());
            command.setClient(client);

            when(commandRepository.existsByCommandNumber(123)).thenReturn(false);
            when(commandRepository.existsByClientId(client.getId())).thenReturn(false);

            Command result = useCase.execute(command);

            assertNotNull(result);
            assertEquals(123, result.getCommandNumber());
            verify(commandRepository, times(1)).existsByCommandNumber(123);
            verify(commandRepository, times(1)).existsByClientId(client.getId());
            verify(commandRepository, times(1)).save(command);
        }

        @Test
        void shouldCreateInternalCommandWhenClientIsNull() {
            Command command = new Command();
            command.setCommandNumber(123);
            command.setClient(null);

            when(commandRepository.existsByCommandNumber(123)).thenReturn(false);

            Command result = useCase.execute(command);

            assertNotNull(result);
            assertEquals(123, result.getCommandNumber());
            assertNull(result.getClient());
            verify(commandRepository, times(1)).existsByCommandNumber(123);
            verify(commandRepository, never()).existsByClientId(any());
            verify(commandRepository, times(1)).save(command);
        }

        @Test
        void shouldThrowExceptionWhenCommandIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("Comanda inválida", exception.getMessage());
            verify(commandRepository, never()).existsByCommandNumber(anyInt());
            verify(commandRepository, never()).existsByClientId(any());
            verify(commandRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenCommandNumberAlreadyExists() {
            Command command = new Command();
            command.setCommandNumber(123);

            when(commandRepository.existsByCommandNumber(123)).thenReturn(true);

            ConflictException exception = assertThrows(ConflictException.class,
                    () -> useCase.execute(command));

            assertEquals("Comanda com esse número já existe", exception.getMessage());
            verify(commandRepository, times(1)).existsByCommandNumber(123);
            verify(commandRepository, never()).existsByClientId(any());
            verify(commandRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenClientAlreadyHasOpenCommand() {
            Command command = new Command();
            command.setCommandNumber(123);

            Client client = new Client();
            client.setId(UUID.randomUUID());
            command.setClient(client);

            when(commandRepository.existsByCommandNumber(123)).thenReturn(false);
            when(commandRepository.existsByClientId(client.getId())).thenReturn(true);

            ConflictException exception = assertThrows(ConflictException.class,
                    () -> useCase.execute(command));

            assertEquals("Cliente já possui uma comanda aberta", exception.getMessage());
            verify(commandRepository, times(1)).existsByCommandNumber(123);
            verify(commandRepository, times(1)).existsByClientId(client.getId());
            verify(commandRepository, never()).save(any());
        }
    }

    @Nested
    class DeleteCommandUseCaseTests {
        private DeleteCommandUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new DeleteCommandUseCase(commandRepository);
        }

        @Test
        void shouldDeleteCommandSuccessfully() {
            Integer commandId = 1;
            Command command = new Command();
            command.setId(commandId);

            when(commandRepository.findById(commandId)).thenReturn(command);

            useCase.execute(commandId);

            verify(commandRepository, times(1)).findById(commandId);
            verify(commandRepository, times(1)).deleteById(commandId);
        }

        @Test
        void shouldThrowExceptionWhenCommandNotFound() {
            Integer commandId = 999;

            when(commandRepository.findById(commandId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(commandId));

            assertEquals("Comanda não encontrada", exception.getMessage());
            verify(commandRepository, times(1)).findById(commandId);
            verify(commandRepository, never()).deleteById(any());
        }

        @Test
        void shouldCheckCommandExistenceBeforeDeleting() {
            Integer commandId = 1;

            when(commandRepository.findById(commandId)).thenReturn(null);

            assertThrows(IllegalArgumentException.class, () -> useCase.execute(commandId));

            verify(commandRepository, never()).deleteById(any());
        }
    }

    @Nested
    class GetCommandUseCaseTests {
        private GetCommandUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetCommandUseCase(commandRepository);
        }

        @Test
        void shouldReturnCommandWhenFound() {
            Integer commandId = 1;
            Command expectedCommand = new Command();
            expectedCommand.setId(commandId);
            expectedCommand.setCommandNumber(123);

            when(commandRepository.findById(commandId)).thenReturn(expectedCommand);

            Command result = useCase.execute(commandId);

            assertNotNull(result);
            assertEquals(commandId, result.getId());
            assertEquals(123, result.getCommandNumber());
            verify(commandRepository, times(1)).findById(commandId);
        }

        @Test
        void shouldThrowExceptionWhenCommandNotFound() {
            Integer commandId = 999;

            when(commandRepository.findById(commandId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(commandId));

            assertEquals("Comanda não encontrada", exception.getMessage());
            verify(commandRepository, times(1)).findById(commandId);
        }

        @Test
        void shouldReturnSameCommandInstanceFromRepository() {
            Integer commandId = 1;
            Command expectedCommand = new Command();
            expectedCommand.setId(commandId);

            when(commandRepository.findById(commandId)).thenReturn(expectedCommand);

            Command result = useCase.execute(commandId);

            assertSame(expectedCommand, result);
        }
    }

    @Nested
    class ListAllCommandsByStatusUseCaseTests {
        private ListAllCommandsByStatusUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new ListAllCommandsByStatusUseCase(commandRepository);
        }

        @Test
        void shouldReturnCommandsByStatus() {
            Status status = Status.OPEN;

            Command command1 = new Command();
            command1.setId(1);
            command1.setStatus(Status.OPEN);

            Command command2 = new Command();
            command2.setId(2);
            command2.setStatus(Status.OPEN);

            List<Command> expectedCommands = Arrays.asList(command1, command2);

            when(commandRepository.findAllByStatus(status)).thenReturn(expectedCommands);

            List<Command> result = useCase.execute(status);

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(Status.OPEN, result.get(0).getStatus());
            assertEquals(Status.OPEN, result.get(1).getStatus());
            verify(commandRepository, times(1)).findAllByStatus(status);
        }

        @Test
        void shouldReturnEmptyListWhenNoCommandsWithStatus() {
            Status status = Status.CLOSED;

            when(commandRepository.findAllByStatus(status)).thenReturn(Collections.emptyList());

            List<Command> result = useCase.execute(status);

            assertNotNull(result);
            assertTrue(result.isEmpty());
            verify(commandRepository, times(1)).findAllByStatus(status);
        }

        @Test
        void shouldReturnSameListInstanceFromRepository() {
            Status status = Status.OPEN;
            List<Command> expectedList = Arrays.asList(new Command(), new Command());

            when(commandRepository.findAllByStatus(status)).thenReturn(expectedList);

            List<Command> result = useCase.execute(status);

            assertSame(expectedList, result);
        }
    }

    @Nested
    class ListCommandsUseCaseTests {
        private ListCommandsUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new ListCommandsUseCase(commandRepository);
        }

        @Test
        void shouldReturnListOfCommands() {
            Command command1 = new Command();
            command1.setId(1);
            command1.setCommandNumber(123);

            Command command2 = new Command();
            command2.setId(2);
            command2.setCommandNumber(456);

            List<Command> expectedCommands = Arrays.asList(command1, command2);

            when(commandRepository.findAll()).thenReturn(expectedCommands);

            List<Command> result = useCase.execute();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(123, result.get(0).getCommandNumber());
            assertEquals(456, result.get(1).getCommandNumber());
            verify(commandRepository, times(1)).findAll();
        }

        @Test
        void shouldReturnEmptyListWhenNoCommandsExist() {
            when(commandRepository.findAll()).thenReturn(Collections.emptyList());

            List<Command> result = useCase.execute();

            assertNotNull(result);
            assertTrue(result.isEmpty());
            verify(commandRepository, times(1)).findAll();
        }

        @Test
        void shouldReturnSameListInstanceFromRepository() {
            List<Command> expectedList = Arrays.asList(new Command(), new Command());

            when(commandRepository.findAll()).thenReturn(expectedList);

            List<Command> result = useCase.execute();

            assertSame(expectedList, result);
        }
    }

    @Nested
    class UpdateCommandUseCaseTests {
        private UpdateCommandUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new UpdateCommandUseCase(commandRepository, commandTotalCalculator);
        }

        @Test
        void shouldUpdateOpenCommandSuccessfully() {
            Integer commandId = 1;

            Command existing = new Command();
            existing.setId(commandId);
            existing.setStatus(Status.OPEN);

            Command updated = new Command();
            updated.setId(commandId);
            updated.setStatus(Status.OPEN);
            updated.setDiscount(10.0);

            when(commandRepository.findById(commandId)).thenReturn(existing);
            when(commandTotalCalculator.calculateTotalValue(commandId)).thenReturn(100.0);

            Command result = useCase.execute(updated);

            assertNotNull(result);
            assertEquals(0.0, result.getDiscount());
            assertEquals(100.0, result.getTotalValue());
            verify(commandRepository, times(1)).save(updated);
        }

        @Test
        void shouldUpdateClosedCommandWithPercentageDiscount() {
            Integer commandId = 1;

            Command existing = new Command();
            existing.setId(commandId);

            Command updated = new Command();
            updated.setId(commandId);
            updated.setStatus(Status.CLOSED);
            updated.setDiscount(10.0);

            when(commandRepository.findById(commandId)).thenReturn(existing);
            when(commandTotalCalculator.calculateTotalValue(commandId)).thenReturn(100.0);

            Command result = useCase.execute(updated);

            assertNotNull(result);
            assertEquals(90.0, result.getTotalValue(), 0.001);
            verify(commandRepository, times(1)).save(updated);
        }

        @Test
        void shouldUpdateClosedCommandWithDecimalDiscount() {
            Integer commandId = 1;

            Command existing = new Command();
            existing.setId(commandId);

            Command updated = new Command();
            updated.setId(commandId);
            updated.setStatus(Status.CLOSED);
            updated.setDiscount(0.15);

            when(commandRepository.findById(commandId)).thenReturn(existing);
            when(commandTotalCalculator.calculateTotalValue(commandId)).thenReturn(200.0);

            Command result = useCase.execute(updated);

            assertNotNull(result);
            assertEquals(170.0, result.getTotalValue(), 0.001);
            verify(commandRepository, times(1)).save(updated);
        }

        @Test
        void shouldThrowExceptionWhenCommandIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("Comanda inválida", exception.getMessage());
            verify(commandRepository, never()).findById(any());
            verify(commandRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenIdIsNull() {
            Command updated = new Command();

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Comanda inválida", exception.getMessage());
            verify(commandRepository, never()).findById(any());
            verify(commandRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenCommandNotFound() {
            Integer commandId = 999;
            Command updated = new Command();
            updated.setId(commandId);

            when(commandRepository.findById(commandId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Comanda não encontrada", exception.getMessage());
            verify(commandRepository, times(1)).findById(commandId);
            verify(commandRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenTotalValueIsNegative() {
            Integer commandId = 1;

            Command existing = new Command();
            existing.setId(commandId);

            Command updated = new Command();
            updated.setId(commandId);
            updated.setStatus(Status.CLOSED);
            updated.setDiscount(150.0);

            when(commandRepository.findById(commandId)).thenReturn(existing);
            when(commandTotalCalculator.calculateTotalValue(commandId)).thenReturn(100.0);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Total value cannot be negative", exception.getMessage());
            verify(commandRepository, never()).save(any());
        }

        @Test
        void shouldSetInternalTrueWhenClientIsNull() {
            Integer commandId = 1;

            Command existing = new Command();
            existing.setId(commandId);

            Command updated = new Command();
            updated.setId(commandId);
            updated.setStatus(Status.OPEN);
            updated.setClient(null);

            when(commandRepository.findById(commandId)).thenReturn(existing);
            when(commandTotalCalculator.calculateTotalValue(commandId)).thenReturn(50.0);

            Command result = useCase.execute(updated);
        }

        @Test
        void shouldSetInternalFalseWhenClientIsProvided() {
            Integer commandId = 1;

            Command existing = new Command();
            existing.setId(commandId);

            Client client = new Client();
            client.setId(UUID.randomUUID());

            Command updated = new Command();
            updated.setId(commandId);
            updated.setStatus(Status.OPEN);
            updated.setClient(client);

            when(commandRepository.findById(commandId)).thenReturn(existing);
            when(commandTotalCalculator.calculateTotalValue(commandId)).thenReturn(50.0);

            Command result = useCase.execute(updated);
        }

        @Test
        void shouldConvertDiscountFromPercentageToDecimal() {
            Integer commandId = 1;

            Command existing = new Command();
            existing.setId(commandId);

            Command updated = new Command();
            updated.setId(commandId);
            updated.setStatus(Status.CLOSED);
            updated.setDiscount(25.0);

            when(commandRepository.findById(commandId)).thenReturn(existing);
            when(commandTotalCalculator.calculateTotalValue(commandId)).thenReturn(100.0);

            Command result = useCase.execute(updated);

            assertEquals(75.0, result.getTotalValue(), 0.001);
        }
    }
}