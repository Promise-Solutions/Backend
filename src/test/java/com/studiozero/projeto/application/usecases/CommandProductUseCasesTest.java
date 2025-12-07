package com.studiozero.projeto.application.usecases;

import com.studiozero.projeto.application.usecases.command.CommandTotalCalculator;
import com.studiozero.projeto.application.usecases.command.GetCommandUseCase;
import com.studiozero.projeto.application.usecases.command.UpdateCommandUseCase;
import com.studiozero.projeto.application.usecases.commandproduct.*;
import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.application.usecases.product.UpdateProductUseCase;
import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.domain.repositories.CommandProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandProductUseCasesTest {

    @Mock
    private CommandProductRepository commandProductRepository;

    @Mock
    private GetProductUseCase getProductUseCase;

    @Mock
    private UpdateProductUseCase updateProductUseCase;

    @Mock
    private UpdateCommandUseCase updateCommandUseCase;

    @Mock
    private GetCommandUseCase getCommandUseCase;

    @Mock
    private CommandTotalCalculator commandTotalCalculator;

    @Nested
    class CreateCommandProductUseCaseTests {
        private CreateCommandProductUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new CreateCommandProductUseCase(
                    commandProductRepository,
                    getProductUseCase,
                    updateProductUseCase,
                    updateCommandUseCase,
                    getCommandUseCase,
                    commandTotalCalculator
            );
        }

        @Test
        void shouldCreateCommandProductForInternalCommandSuccessfully() {
            CommandProduct commandProduct = new CommandProduct();
            commandProduct.setProductQuantity(5);

            Product product = new Product();
            product.setId(123);
            product.setQuantity(10);
            product.setInternalValue(20.0);
            product.setClientValue(25.0);
            commandProduct.setProduct(product);

            Command command = new Command();
            command.setId(1);
            command.setInternal(true);
            command.setTotalValue(0.0);
            commandProduct.setCommand(command);

            when(getProductUseCase.execute(product.getId())).thenReturn(product);
            when(getCommandUseCase.execute(1)).thenReturn(command);
            when(commandTotalCalculator.calculateTotalValue(1)).thenReturn(100.0);

            CommandProduct result = useCase.execute(commandProduct);

            assertNotNull(result);
            assertEquals(5, product.getQuantity());
            verify(updateProductUseCase, times(1)).execute(product);
            verify(commandProductRepository, times(1)).save(commandProduct);
            verify(updateCommandUseCase, times(2)).execute(command);
            verify(commandTotalCalculator, times(1)).calculateTotalValue(1);
        }

        @Test
        void shouldCreateCommandProductForClientCommandSuccessfully() {
            CommandProduct commandProduct = new CommandProduct();
            commandProduct.setProductQuantity(3);

            Product product = new Product();
            product.setId(123);
            product.setQuantity(10);
            product.setInternalValue(20.0);
            product.setClientValue(30.0);
            commandProduct.setProduct(product);

            Command command = new Command();
            command.setId(1);
            command.setInternal(false);
            command.setTotalValue(0.0);
            commandProduct.setCommand(command);

            when(getProductUseCase.execute(product.getId())).thenReturn(product);
            when(getCommandUseCase.execute(1)).thenReturn(command);
            when(commandTotalCalculator.calculateTotalValue(1)).thenReturn(90.0);

            CommandProduct result = useCase.execute(commandProduct);

            assertNotNull(result);
            assertEquals(7, product.getQuantity());
            verify(updateProductUseCase, times(1)).execute(product);
            verify(commandProductRepository, times(1)).save(commandProduct);
        }

        @Test
        void shouldThrowExceptionWhenCommandProductIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("CommandProduct cannot be null", exception.getMessage());
            verify(commandProductRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenInsufficientProductQuantity() {
            CommandProduct commandProduct = new CommandProduct();
            commandProduct.setProductQuantity(15);

            Product product = new Product();
            product.setId(123);
            product.setQuantity(10);
            commandProduct.setProduct(product);

            Command command = new Command();
            command.setId(1);
            commandProduct.setCommand(command);

            when(getProductUseCase.execute(product.getId())).thenReturn(product);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(commandProduct));

            assertEquals("Insufficient product quantity", exception.getMessage());
            verify(commandProductRepository, never()).save(any());
        }

        @Test
        void shouldUpdateCommandTotalValueAfterCreation() {
            CommandProduct commandProduct = new CommandProduct();
            commandProduct.setProductQuantity(2);

            Product product = new Product();
            product.setId(123);
            product.setQuantity(10);
            product.setInternalValue(50.0);
            commandProduct.setProduct(product);

            Command command = new Command();
            command.setId(1);
            command.setInternal(true);
            command.setTotalValue(100.0);
            commandProduct.setCommand(command);

            when(getProductUseCase.execute(product.getId())).thenReturn(product);
            when(getCommandUseCase.execute(1)).thenReturn(command);
            when(commandTotalCalculator.calculateTotalValue(1)).thenReturn(250.0);

            useCase.execute(commandProduct);

            verify(commandTotalCalculator, times(1)).calculateTotalValue(1);
            verify(updateCommandUseCase, times(2)).execute(command);
        }
    }

    @Nested
    class DeleteCommandProductUseCaseTests {
        private DeleteCommandProductUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new DeleteCommandProductUseCase(
                    commandProductRepository,
                    getProductUseCase,
                    updateProductUseCase,
                    updateCommandUseCase,
                    getCommandUseCase,
                    commandTotalCalculator
            );
        }

        @Test
        void shouldDeleteCommandProductSuccessfully() {
            Integer commandProductId = 1;

            CommandProduct commandProduct = new CommandProduct();
            commandProduct.setId(commandProductId);
            commandProduct.setProductQuantity(5);
            commandProduct.setUnitValue(20.0);

            Product product = new Product();
            product.setId(123);
            product.setQuantity(10);
            commandProduct.setProduct(product);

            Command command = new Command();
            command.setId(1);
            command.setTotalValue(200.0);
            commandProduct.setCommand(command);

            when(commandProductRepository.findById(commandProductId)).thenReturn(commandProduct);
            when(getProductUseCase.execute(product.getId())).thenReturn(product);
            when(getCommandUseCase.execute(1)).thenReturn(command);
            when(commandTotalCalculator.calculateTotalValue(1)).thenReturn(100.0);

            useCase.execute(commandProductId);

            assertEquals(15, product.getQuantity());
            verify(updateProductUseCase, times(1)).execute(product);
            verify(updateCommandUseCase, times(2)).execute(command);
            verify(commandProductRepository, times(1)).deleteById(commandProductId);
            verify(commandTotalCalculator, times(1)).calculateTotalValue(1);
        }

        @Test
        void shouldThrowExceptionWhenCommandProductNotFound() {
            Integer commandProductId = 999;

            when(commandProductRepository.findById(commandProductId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(commandProductId));

            assertEquals("CommandProduct not found", exception.getMessage());
            verify(commandProductRepository, never()).deleteById(any());
        }

        @Test
        void shouldThrowExceptionWhenIdIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("CommandProduct not found", exception.getMessage());
            verify(commandProductRepository, never()).deleteById(any());
        }

        @Test
        void shouldRestoreProductQuantityWhenDeleting() {
            Integer commandProductId = 1;

            CommandProduct commandProduct = new CommandProduct();
            commandProduct.setId(commandProductId);
            commandProduct.setProductQuantity(8);
            commandProduct.setUnitValue(15.0);

            Product product = new Product();
            product.setId(123);
            product.setQuantity(5);
            commandProduct.setProduct(product);

            Command command = new Command();
            command.setId(1);
            command.setTotalValue(120.0);
            commandProduct.setCommand(command);

            when(commandProductRepository.findById(commandProductId)).thenReturn(commandProduct);
            when(getProductUseCase.execute(product.getId())).thenReturn(product);
            when(getCommandUseCase.execute(1)).thenReturn(command);
            when(commandTotalCalculator.calculateTotalValue(1)).thenReturn(0.0);

            useCase.execute(commandProductId);

            assertEquals(13, product.getQuantity());
        }
    }

    @Nested
    class GetCommandProductUseCaseTests {
        private GetCommandProductUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetCommandProductUseCase(commandProductRepository);
        }

        @Test
        void shouldReturnCommandProductWhenFound() {
            Integer commandProductId = 1;
            CommandProduct expectedCommandProduct = new CommandProduct();
            expectedCommandProduct.setId(commandProductId);
            expectedCommandProduct.setProductQuantity(5);

            when(commandProductRepository.findById(commandProductId)).thenReturn(expectedCommandProduct);

            CommandProduct result = useCase.execute(commandProductId);

            assertNotNull(result);
            assertEquals(commandProductId, result.getId());
            assertEquals(5, result.getProductQuantity());
            verify(commandProductRepository, times(1)).findById(commandProductId);
        }

        @Test
        void shouldThrowExceptionWhenCommandProductNotFound() {
            Integer commandProductId = 999;

            when(commandProductRepository.findById(commandProductId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(commandProductId));

            assertEquals("CommandProduct not found", exception.getMessage());
            verify(commandProductRepository, times(1)).findById(commandProductId);
        }

        @Test
        void shouldReturnSameCommandProductInstanceFromRepository() {
            Integer commandProductId = 1;
            CommandProduct expectedCommandProduct = new CommandProduct();
            expectedCommandProduct.setId(commandProductId);

            when(commandProductRepository.findById(commandProductId)).thenReturn(expectedCommandProduct);

            CommandProduct result = useCase.execute(commandProductId);

            assertSame(expectedCommandProduct, result);
        }
    }

    @Nested
    class ListCommandProductsUseCaseTests {
        private ListCommandProductsUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new ListCommandProductsUseCase(commandProductRepository);
        }

        @Test
        void shouldReturnListOfCommandProductsForCommand() {
            Integer commandId = 1;

            CommandProduct cp1 = new CommandProduct();
            cp1.setId(1);
            cp1.setProductQuantity(5);

            CommandProduct cp2 = new CommandProduct();
            cp2.setId(2);
            cp2.setProductQuantity(3);

            List<CommandProduct> expectedList = Arrays.asList(cp1, cp2);

            when(commandProductRepository.findAllByCommand_Id(commandId)).thenReturn(expectedList);

            List<CommandProduct> result = useCase.execute(commandId);

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(5, result.get(0).getProductQuantity());
            assertEquals(3, result.get(1).getProductQuantity());
            verify(commandProductRepository, times(1)).findAllByCommand_Id(commandId);
        }

        @Test
        void shouldReturnEmptyListWhenNoCommandProductsExist() {
            Integer commandId = 999;

            when(commandProductRepository.findAllByCommand_Id(commandId)).thenReturn(Collections.emptyList());

            List<CommandProduct> result = useCase.execute(commandId);

            assertNotNull(result);
            assertTrue(result.isEmpty());
            verify(commandProductRepository, times(1)).findAllByCommand_Id(commandId);
        }
    }

    @Nested
    class UpdateCommandProductUseCaseTests {
        private UpdateCommandProductUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new UpdateCommandProductUseCase(
                    commandProductRepository,
                    getProductUseCase,
                    updateProductUseCase,
                    updateCommandUseCase,
                    getCommandUseCase,
                    commandTotalCalculator
            );
        }

        @Test
        void shouldUpdateCommandProductSuccessfully() {
            Integer commandProductId = 1;

            CommandProduct existing = new CommandProduct();
            existing.setId(commandProductId);
            existing.setProductQuantity(5);

            CommandProduct updated = new CommandProduct();
            updated.setId(commandProductId);
            updated.setProductQuantity(8);

            Product product = new Product();
            product.setId(123);
            product.setQuantity(10);
            updated.setProduct(product);

            Command command = new Command();
            command.setId(1);
            updated.setCommand(command);

            when(commandProductRepository.findById(commandProductId)).thenReturn(existing);
            when(getProductUseCase.execute(product.getId())).thenReturn(product);
            when(getCommandUseCase.execute(1)).thenReturn(command);
            when(commandTotalCalculator.calculateTotalValue(1)).thenReturn(150.0);

            CommandProduct result = useCase.execute(updated);

            assertNotNull(result);
            assertEquals(7, product.getQuantity());
            verify(updateProductUseCase, times(1)).execute(product);
            verify(commandProductRepository, times(1)).save(updated);
            verify(commandTotalCalculator, times(1)).calculateTotalValue(1);
        }

        @Test
        void shouldThrowExceptionWhenCommandProductIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("CommandProduct and id cannot be null", exception.getMessage());
            verify(commandProductRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenIdIsNull() {
            CommandProduct updated = new CommandProduct();

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("CommandProduct and id cannot be null", exception.getMessage());
            verify(commandProductRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenCommandProductNotFound() {
            CommandProduct updated = new CommandProduct();
            updated.setId(999);

            when(commandProductRepository.findById(999)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("CommandProduct not found", exception.getMessage());
            verify(commandProductRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenNotEnoughStock() {
            Integer commandProductId = 1;

            CommandProduct existing = new CommandProduct();
            existing.setId(commandProductId);
            existing.setProductQuantity(5);

            CommandProduct updated = new CommandProduct();
            updated.setId(commandProductId);
            updated.setProductQuantity(20);

            Product product = new Product();
            product.setId(123);
            product.setQuantity(10);
            updated.setProduct(product);

            when(commandProductRepository.findById(commandProductId)).thenReturn(existing);
            when(getProductUseCase.execute(product.getId())).thenReturn(product);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Not enough product in stock", exception.getMessage());
            verify(commandProductRepository, never()).save(any());
        }

        @Test
        void shouldIncreaseStockWhenDecreasingQuantity() {
            Integer commandProductId = 1;

            CommandProduct existing = new CommandProduct();
            existing.setId(commandProductId);
            existing.setProductQuantity(10);

            CommandProduct updated = new CommandProduct();
            updated.setId(commandProductId);
            updated.setProductQuantity(5);

            Product product = new Product();
            product.setId(123);
            product.setQuantity(20);
            updated.setProduct(product);

            Command command = new Command();
            command.setId(1);
            updated.setCommand(command);

            when(commandProductRepository.findById(commandProductId)).thenReturn(existing);
            when(getProductUseCase.execute(product.getId())).thenReturn(product);
            when(getCommandUseCase.execute(1)).thenReturn(command);
            when(commandTotalCalculator.calculateTotalValue(1)).thenReturn(100.0);

            useCase.execute(updated);

            assertEquals(25, product.getQuantity());
        }

        @Test
        void shouldUpdateCommandTotalValueAfterUpdate() {
            Integer commandProductId = 1;

            CommandProduct existing = new CommandProduct();
            existing.setId(commandProductId);
            existing.setProductQuantity(5);

            CommandProduct updated = new CommandProduct();
            updated.setId(commandProductId);
            updated.setProductQuantity(7);

            Product product = new Product();
            product.setId(123);
            product.setQuantity(10);
            updated.setProduct(product);

            Command command = new Command();
            command.setId(1);
            updated.setCommand(command);

            when(commandProductRepository.findById(commandProductId)).thenReturn(existing);
            when(getProductUseCase.execute(product.getId())).thenReturn(product);
            when(getCommandUseCase.execute(1)).thenReturn(command);
            when(commandTotalCalculator.calculateTotalValue(1)).thenReturn(200.0);

            useCase.execute(updated);

            verify(commandTotalCalculator, times(1)).calculateTotalValue(1);
            verify(updateCommandUseCase, times(1)).execute(command);
        }
    }
}
