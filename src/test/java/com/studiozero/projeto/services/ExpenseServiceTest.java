package com.studiozero.projeto.services;

import static org.junit.jupiter.api.Assertions.*;

import com.studiozero.projeto.application.services.ExpenseService;
import com.studiozero.projeto.application.services.ProductService;
import com.studiozero.projeto.application.dtos.request.ExpenseRequestDTO;
import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.application.enums.ExpenseCategory;
import com.studiozero.projeto.infrastructure.exceptions.NotFoundException;
import com.studiozero.projeto.web.mappers.ExpenseMapper;
import com.studiozero.projeto.domain.repositories.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private ExpenseMapper expenseMapper;

    @InjectMocks
    private ExpenseService expenseService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create expense and update product quantity if STOCK category")
    void testCreateExpense_StockCategory() {
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setExpenseCategory(ExpenseCategory.STOCK);
        dto.setFkProduct(1);
        dto.setQuantity(5);

        Expense expense = new Expense();
        Product product = new Product();
        product.setQuantity(10);

        when(expenseMapper.toEntity(dto)).thenReturn(expense);
        when(productService.findProductById(1)).thenReturn(product);
        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense created = expenseService.createExpense(dto);

        assertEquals(15, product.getQuantity());
        verify(productService).updateProduct(product);

        assertEquals(expense, created);
        verify(expenseRepository).save(expense);
    }

    @Test
    @DisplayName("Should create expense without updating product if not STOCK category")
    void testCreateExpense_NonStockCategory() {
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setExpenseCategory(ExpenseCategory.OTHERS);

        Expense expense = new Expense();

        when(expenseMapper.toEntity(dto)).thenReturn(expense);
        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense created = expenseService.createExpense(dto);

        verify(productService, never()).findProductById(any());
        verify(productService, never()).updateProduct(any());
        verify(expenseRepository).save(expense);

        assertEquals(expense, created);
    }

    @Test
    @DisplayName("Should return all expenses")
    void testExpenseList() {
        List<Expense> expenses = List.of(new Expense(), new Expense());

        when(expenseRepository.findAll()).thenReturn(expenses);

        List<Expense> result = expenseService.expenseList();

        assertEquals(expenses, result);
        verify(expenseRepository).findAll();
    }

    @Test
    @DisplayName("Should find expense by id")
    void testFindExpenseById_Success() {
        Expense expense = new Expense();
        when(expenseRepository.findById(1)).thenReturn(Optional.of(expense));

        Expense result = expenseService.findExpenseById(1);

        assertEquals(expense, result);
    }

    @Test
    @DisplayName("Should throw NotFoundException when expense not found by id")
    void testFindExpenseById_NotFound() {
        when(expenseRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> expenseService.findExpenseById(1));
    }

    @Test
    @DisplayName("Should update expense and adjust product quantity if STOCK category and quantity changed")
    void testUpdateExpense_StockCategory_QuantityChanged() {
        Expense expenseToUpdate = new Expense();
        expenseToUpdate.setId(1);
        expenseToUpdate.setExpenseCategory(ExpenseCategory.STOCK);
        expenseToUpdate.setQuantity(15);

        Product product = new Product();
        product.setQuantity(50);
        product.setId(2);

        expenseToUpdate.setProduct(product);

        Expense prevExpense = new Expense();
        prevExpense.setQuantity(10);
        prevExpense.setProduct(product);

        when(expenseRepository.existsById(1)).thenReturn(true);
        when(expenseRepository.findById(1)).thenReturn(Optional.of(prevExpense));
        when(productService.findProductById(product.getId())).thenReturn(product);
        when(expenseRepository.save(expenseToUpdate)).thenReturn(expenseToUpdate);

        Expense updated = expenseService.updateExpense(expenseToUpdate);

        assertEquals(55, product.getQuantity());
        verify(productService).updateProduct(product);
        verify(expenseRepository).save(expenseToUpdate);

        assertEquals(expenseToUpdate, updated);
    }


    @Test
    @DisplayName("Should update expense without adjusting product quantity if quantity unchanged")
    void testUpdateExpense_StockCategory_QuantityUnchanged() {
        Expense expenseToUpdate = new Expense();
        expenseToUpdate.setId(1);
        expenseToUpdate.setExpenseCategory(ExpenseCategory.STOCK);
        expenseToUpdate.setQuantity(10);
        Expense prevExpense = new Expense();
        prevExpense.setQuantity(10);

        when(expenseRepository.existsById(1)).thenReturn(true);
        when(expenseRepository.findById(1)).thenReturn(Optional.of(prevExpense));
        when(expenseRepository.save(expenseToUpdate)).thenReturn(expenseToUpdate);

        Expense updated = expenseService.updateExpense(expenseToUpdate);

        verify(productService, never()).updateProduct(any());
        verify(productService, never()).findProductById(any());
        verify(expenseRepository).save(expenseToUpdate);

        assertEquals(expenseToUpdate, updated);
    }

    @Test
    @DisplayName("Should update expense without product adjustment if not STOCK category")
    void testUpdateExpense_NonStockCategory() {
        Expense expenseToUpdate = new Expense();
        expenseToUpdate.setId(1);
        expenseToUpdate.setExpenseCategory(ExpenseCategory.OTHERS);

        when(expenseRepository.existsById(1)).thenReturn(true);
        when(expenseRepository.save(expenseToUpdate)).thenReturn(expenseToUpdate);

        Expense updated = expenseService.updateExpense(expenseToUpdate);

        verify(productService, never()).updateProduct(any());
        verify(productService, never()).findProductById(any());
        verify(expenseRepository).save(expenseToUpdate);

        assertEquals(expenseToUpdate, updated);
    }

    @Test
    @DisplayName("Should throw NotFoundException when updating non-existent expense")
    void testUpdateExpense_NotFound() {
        Expense expense = new Expense();
        expense.setId(1);

        when(expenseRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> expenseService.updateExpense(expense));
        verify(expenseRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete expense when exists")
    void testDeleteExpense_Success() {
        when(expenseRepository.existsById(1)).thenReturn(true);

        expenseService.deleteExpense(1);

        verify(expenseRepository).deleteById(1);
    }

    @Test
    @DisplayName("Should throw NotFoundException when deleting non-existent expense")
    void testDeleteExpense_NotFound() {
        when(expenseRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> expenseService.deleteExpense(1));

        verify(expenseRepository, never()).deleteById(any());
    }
}
