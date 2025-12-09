package com.studiozero.projeto.application.usecases;

import com.studiozero.projeto.application.enums.ExpenseCategory;
import com.studiozero.projeto.application.enums.PaymentType;
import com.studiozero.projeto.application.usecases.expense.*;
import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.application.usecases.product.UpdateProductUseCase;
import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.domain.repositories.ExpenseRepository;
import com.studiozero.projeto.domain.repositories.ProductRepository;
import com.studiozero.projeto.web.dtos.request.ExpenseRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseUseCasesTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private GetProductUseCase getProductUseCase;

    @Mock
    private UpdateProductUseCase updateProductUseCase;

    @Nested
    class CreateExpenseUseCaseTests {
        private CreateExpenseUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new CreateExpenseUseCase(expenseRepository, productRepository);
        }

        @Test
        void shouldCreateStockExpenseWithProductSuccessfully() {
            Product product = new Product();
            product.setId(123);
            product.setQuantity(10);

            ExpenseRequestDTO expenseDto = new ExpenseRequestDTO();
            expenseDto.setDate(LocalDate.now());
            expenseDto.setExpenseCategory(ExpenseCategory.STOCK);
            expenseDto.setDescription("Stock purchase");
            expenseDto.setQuantity(5);
            expenseDto.setAmountSpend(100.0);
            expenseDto.setFkProduct(123);
            expenseDto.setPaymentType(PaymentType.PIX);

            when(productRepository.findById(123)).thenReturn(product);

            Expense result = useCase.execute(expenseDto);

            assertNotNull(result);
            assertEquals(ExpenseCategory.STOCK, result.getExpenseCategory());
            assertEquals("Stock purchase", result.getDescription());
            assertEquals(5, result.getQuantity());
            assertEquals(100.0, result.getAmountSpend());
            assertNotNull(result.getProduct());
            verify(productRepository, times(1)).findById(123);
            verify(productRepository, times(1)).save(product);
            verify(expenseRepository, times(1)).save(any(Expense.class));
        }

        @Test
        void shouldCreateNonStockExpenseWithoutProductSuccessfully() {
            ExpenseRequestDTO expenseDto = new ExpenseRequestDTO();
            expenseDto.setDate(LocalDate.now());
            expenseDto.setExpenseCategory(ExpenseCategory.OTHERS);
            expenseDto.setDescription("Office supplies");
            expenseDto.setQuantity(1);
            expenseDto.setAmountSpend(50.0);
            expenseDto.setPaymentType(PaymentType.CREDIT_CARD);

            Expense result = useCase.execute(expenseDto);

            assertNotNull(result);
            assertEquals(ExpenseCategory.OTHERS, result.getExpenseCategory());
            assertEquals("Office supplies", result.getDescription());
            assertNull(result.getProduct());
            verify(productRepository, never()).findById(any());
            verify(productRepository, never()).save(any());
            verify(expenseRepository, times(1)).save(any(Expense.class));
        }

        @Test
        void shouldThrowExceptionWhenExpenseDtoIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("Despesa inválida", exception.getMessage());
            verify(expenseRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenProductNotFoundForStockExpense() {

            ExpenseRequestDTO expenseDto = new ExpenseRequestDTO();
            expenseDto.setDate(LocalDate.now());
            expenseDto.setExpenseCategory(ExpenseCategory.STOCK);
            expenseDto.setDescription("Stock purchase");
            expenseDto.setQuantity(5);
            expenseDto.setAmountSpend(100.0);
            expenseDto.setFkProduct(123);
            expenseDto.setPaymentType(PaymentType.MONEY);

            when(productRepository.findById(123)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(expenseDto));

            assertEquals("Produto não encontrado para o registro da despesa", exception.getMessage());
            verify(productRepository, times(1)).findById(123);
            verify(productRepository, never()).save(any());
            verify(expenseRepository, never()).save(any());
        }

        @Test
        void shouldIncreaseProductQuantityForStockExpense() {
            Product product = new Product();
            product.setId(123);
            product.setQuantity(10);

            ExpenseRequestDTO expenseDto = new ExpenseRequestDTO();
            expenseDto.setDate(LocalDate.now());
            expenseDto.setExpenseCategory(ExpenseCategory.STOCK);
            expenseDto.setDescription("Stock purchase");
            expenseDto.setQuantity(7);
            expenseDto.setAmountSpend(150.0);
            expenseDto.setFkProduct(123);
            expenseDto.setPaymentType(PaymentType.BILLET);

            when(productRepository.findById(123)).thenReturn(product);

            useCase.execute(expenseDto);

            verify(productRepository, times(1)).save(product);
        }

        @Test
        void shouldCreateExpenseWithNullProduct() {
            ExpenseRequestDTO expenseDto = new ExpenseRequestDTO();
            expenseDto.setDate(LocalDate.now());
            expenseDto.setExpenseCategory(ExpenseCategory.MAINTENANCE);
            expenseDto.setDescription("Equipment maintenance");
            expenseDto.setQuantity(1);
            expenseDto.setAmountSpend(200.0);
            expenseDto.setFkProduct(null);
            expenseDto.setPaymentType(PaymentType.DEBIT_CARD);

            Expense result = useCase.execute(expenseDto);

            assertNotNull(result);
            assertNull(result.getProduct());
            verify(productRepository, never()).findById(any());
        }
    }

    @Nested
    class DeleteExpenseUseCaseTests {
        private DeleteExpenseUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new DeleteExpenseUseCase(expenseRepository);
        }

        @Test
        void shouldDeleteExpenseSuccessfully() {
            Integer expenseId = 1;
            Expense expense = new Expense();
            expense.setId(expenseId);

            when(expenseRepository.findById(expenseId)).thenReturn(expense);

            useCase.execute(expenseId);

            verify(expenseRepository, times(1)).findById(expenseId);
            verify(expenseRepository, times(1)).deleteById(expenseId);
        }

        @Test
        void shouldThrowExceptionWhenExpenseNotFound() {
            Integer expenseId = 999;

            when(expenseRepository.findById(expenseId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(expenseId));

            assertEquals("Despesa não encontrada", exception.getMessage());
            verify(expenseRepository, times(1)).findById(expenseId);
            verify(expenseRepository, never()).deleteById(any());
        }

        @Test
        void shouldCheckExpenseExistenceBeforeDeleting() {
            Integer expenseId = 1;

            when(expenseRepository.findById(expenseId)).thenReturn(null);

            assertThrows(IllegalArgumentException.class, () -> useCase.execute(expenseId));

            verify(expenseRepository, never()).deleteById(any());
        }
    }

    @Nested
    class GetExpenseUseCaseTests {
        private GetExpenseUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetExpenseUseCase(expenseRepository);
        }

        @Test
        void shouldReturnExpenseWhenFound() {
            Integer expenseId = 1;
            Expense expectedExpense = new Expense();
            expectedExpense.setId(expenseId);
            expectedExpense.setDescription("Test expense");
            expectedExpense.setAmountSpend(100.0);

            when(expenseRepository.findById(expenseId)).thenReturn(expectedExpense);

            Expense result = useCase.execute(expenseId);

            assertNotNull(result);
            assertEquals(expenseId, result.getId());
            assertEquals("Test expense", result.getDescription());
            assertEquals(100.0, result.getAmountSpend());
            verify(expenseRepository, times(1)).findById(expenseId);
        }

        @Test
        void shouldThrowExceptionWhenExpenseNotFound() {
            Integer expenseId = 999;

            when(expenseRepository.findById(expenseId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(expenseId));

            assertEquals("Despesa não encontrada", exception.getMessage());
            verify(expenseRepository, times(1)).findById(expenseId);
        }

        @Test
        void shouldReturnSameExpenseInstanceFromRepository() {
            Integer expenseId = 1;
            Expense expectedExpense = new Expense();
            expectedExpense.setId(expenseId);

            when(expenseRepository.findById(expenseId)).thenReturn(expectedExpense);

            Expense result = useCase.execute(expenseId);

            assertSame(expectedExpense, result);
        }
    }

    @Nested
    class ListExpensesUseCaseTests {
        private ListExpensesUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new ListExpensesUseCase(expenseRepository);
        }

        @Test
        void shouldReturnPageOfExpenses() {
            Expense expense1 = new Expense();
            expense1.setId(1);
            expense1.setDescription("Expense 1");

            Expense expense2 = new Expense();
            expense2.setId(2);
            expense2.setDescription("Expense 2");

            Pageable pageable = PageRequest.of(0, 10);
            Page<Expense> expectedPage = new PageImpl<>(Arrays.asList(expense1, expense2), pageable, 2);

            when(expenseRepository.findAll(pageable)).thenReturn(expectedPage);

            Page<Expense> result = useCase.execute(pageable);

            assertNotNull(result);
            assertEquals(2, result.getContent().size());
            assertEquals("Expense 1", result.getContent().get(0).getDescription());
            assertEquals("Expense 2", result.getContent().get(1).getDescription());
            verify(expenseRepository, times(1)).findAll(pageable);
        }

        @Test
        void shouldReturnEmptyPageWhenNoExpensesExist() {
            Pageable pageable = PageRequest.of(0, 10);
            Page<Expense> expectedPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

            when(expenseRepository.findAll(pageable)).thenReturn(expectedPage);

            Page<Expense> result = useCase.execute(pageable);

            assertNotNull(result);
            assertTrue(result.getContent().isEmpty());
            assertEquals(0, result.getTotalElements());
            verify(expenseRepository, times(1)).findAll(pageable);
        }

        @Test
        void shouldReturnSamePageInstanceFromRepository() {
            Pageable pageable = PageRequest.of(0, 10);
            Page<Expense> expectedPage = new PageImpl<>(Arrays.asList(new Expense(), new Expense()));

            when(expenseRepository.findAll(pageable)).thenReturn(expectedPage);

            Page<Expense> result = useCase.execute(pageable);

            assertSame(expectedPage, result);
        }

        @Test
        void shouldHandleDifferentPageSizes() {
            Pageable pageable = PageRequest.of(0, 5);
            Page<Expense> expectedPage = new PageImpl<>(Arrays.asList(new Expense(), new Expense()), pageable, 2);

            when(expenseRepository.findAll(pageable)).thenReturn(expectedPage);

            Page<Expense> result = useCase.execute(pageable);

            assertNotNull(result);
            assertEquals(5, result.getSize());
            verify(expenseRepository, times(1)).findAll(pageable);
        }
    }

    @Nested
    class UpdateExpenseUseCaseTests {
        private UpdateExpenseUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new UpdateExpenseUseCase(expenseRepository, updateProductUseCase, getProductUseCase);
        }

        @Test
        void shouldUpdateExpenseWithoutProductSuccessfully() {
            Integer expenseId = 1;

            Expense existing = new Expense();
            existing.setId(expenseId);
            existing.setDescription("Old description");

            Expense updated = new Expense();
            updated.setId(expenseId);
            updated.setDescription("New description");
            updated.setExpenseCategory(ExpenseCategory.STOCK);

            when(expenseRepository.findById(expenseId)).thenReturn(existing);

            Expense result = useCase.execute(updated);

            assertNotNull(result);
            assertEquals(expenseId, result.getId());
            verify(expenseRepository, times(1)).findById(expenseId);
            verify(expenseRepository, times(1)).save(updated);
            verify(getProductUseCase, never()).execute(any());
            verify(updateProductUseCase, never()).execute(any());
        }

        @Test
        void shouldUpdateExpenseWithStockProductSuccessfully() {
            Integer expenseId = 1;

            Product product = new Product();
            product.setId(123);
            product.setQuantity(20);

            Expense existing = new Expense();
            existing.setId(expenseId);

            Expense updated = new Expense();
            updated.setId(expenseId);
            updated.setExpenseCategory(ExpenseCategory.STOCK);
            updated.setProduct(product);

            when(expenseRepository.findById(expenseId)).thenReturn(existing);
            when(getProductUseCase.execute(123)).thenReturn(product);

            Expense result = useCase.execute(updated);

            assertNotNull(result);
            verify(getProductUseCase, times(1)).execute(123);
            verify(updateProductUseCase, times(1)).execute(product);
            verify(expenseRepository, times(1)).save(updated);
        }

        @Test
        void shouldThrowExceptionWhenExpenseIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("Despesa inválida", exception.getMessage());
            verify(expenseRepository, never()).findById(any());
            verify(expenseRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenIdIsNull() {
            Expense updated = new Expense();

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Despesa inválida", exception.getMessage());
            verify(expenseRepository, never()).findById(any());
            verify(expenseRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenExpenseNotFound() {
            Integer expenseId = 999;
            Expense updated = new Expense();
            updated.setId(expenseId);

            when(expenseRepository.findById(expenseId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Despesa não encontrada", exception.getMessage());
            verify(expenseRepository, times(1)).findById(expenseId);
            verify(expenseRepository, never()).save(any());
        }

        @Test
        void shouldNotUpdateProductForNonStockExpense() {
            Integer expenseId = 1;

            Product product = new Product();
            product.setId(123);

            Expense existing = new Expense();
            existing.setId(expenseId);

            Expense updated = new Expense();
            updated.setId(expenseId);
            updated.setExpenseCategory(ExpenseCategory.MAINTENANCE);
            updated.setProduct(product);

            when(expenseRepository.findById(expenseId)).thenReturn(existing);

            useCase.execute(updated);

            verify(getProductUseCase, never()).execute(any());
            verify(updateProductUseCase, never()).execute(any());
        }

        @Test
        void shouldUpdateProductQuantityForStockExpense() {
            Integer expenseId = 1;

            Product product = new Product();
            product.setId(123);
            product.setQuantity(50);

            Expense existing = new Expense();
            existing.setId(expenseId);

            Expense updated = new Expense();
            updated.setId(expenseId);
            updated.setExpenseCategory(ExpenseCategory.STOCK);
            updated.setProduct(product);

            when(expenseRepository.findById(expenseId)).thenReturn(existing);
            when(getProductUseCase.execute(123)).thenReturn(product);

            useCase.execute(updated);

            assertEquals(50, product.getQuantity());
            verify(updateProductUseCase, times(1)).execute(product);
        }

        @Test
        void shouldReturnUpdatedExpenseInstance() {
            Integer expenseId = 1;

            Expense existing = new Expense();
            existing.setId(expenseId);

            Expense updated = new Expense();
            updated.setId(expenseId);
            updated.setExpenseCategory(ExpenseCategory.MAINTENANCE);

            when(expenseRepository.findById(expenseId)).thenReturn(existing);

            Expense result = useCase.execute(updated);

            assertSame(updated, result);
        }
    }
}
