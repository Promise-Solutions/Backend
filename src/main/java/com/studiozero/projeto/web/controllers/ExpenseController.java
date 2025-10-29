package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.web.dtos.request.ExpenseRequestDTO;
import com.studiozero.projeto.web.dtos.response.ExpenseResponseDTO;
import com.studiozero.projeto.web.mappers.ExpenseMapper;
import com.studiozero.projeto.application.usecases.expense.CreateExpenseUseCase;
import com.studiozero.projeto.application.usecases.expense.GetExpenseUseCase;
import com.studiozero.projeto.application.usecases.expense.UpdateExpenseUseCase;
import com.studiozero.projeto.application.usecases.expense.DeleteExpenseUseCase;
import com.studiozero.projeto.application.usecases.expense.ListExpensesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@Tag(name = "Expenses", description = "Endpoints for Expenses Management")

public class ExpenseController {

    private final CreateExpenseUseCase createExpenseUseCase;
    private final GetExpenseUseCase getExpenseUseCase;
    private final UpdateExpenseUseCase updateExpenseUseCase;
    private final DeleteExpenseUseCase deleteExpenseUseCase;
    private final ListExpensesUseCase listExpensesUseCase;
    private final GetProductUseCase getProductUseCase;

    public ExpenseController(CreateExpenseUseCase createExpenseUseCase, GetExpenseUseCase getExpenseUseCase, UpdateExpenseUseCase updateExpenseUseCase, DeleteExpenseUseCase deleteExpenseUseCase, ListExpensesUseCase listExpensesUseCase, GetProductUseCase getProductUseCase) {
        this.createExpenseUseCase = createExpenseUseCase;
        this.getExpenseUseCase = getExpenseUseCase;
        this.updateExpenseUseCase = updateExpenseUseCase;
        this.deleteExpenseUseCase = deleteExpenseUseCase;
        this.listExpensesUseCase = listExpensesUseCase;
        this.getProductUseCase = getProductUseCase;
    }

    @Operation(summary = "Create a new Expense", description = "This endpoint is resposable to create a new expense")
    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> createExpense(@RequestBody @Valid ExpenseRequestDTO expenseDTO) {
        Product product = getProductUseCase.execute(expenseDTO.getFkProduct());
        Expense expense = ExpenseMapper.toDomain(expenseDTO, product);
        createExpenseUseCase.execute(expense);
        return ResponseEntity.status(201).body(ExpenseMapper.toDTO(expense));
    }

    @Operation(summary = "Search for an Expense", description = "This endpoint is resposable to return a specific expense")
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> findExpenseById(@PathVariable Integer id) {
        Expense expense = getExpenseUseCase.execute(id);
        return ResponseEntity.ok(ExpenseMapper.toDTO(expense));
    }

    @Operation(summary = "List all expenses", description = "This endpoint will list all expenses with pagination.")
    @GetMapping
    public ResponseEntity<Page<ExpenseResponseDTO>> listAllExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Expense> expenses = listExpensesUseCase.execute(PageRequest.of(page, size));
        if (expenses.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        Page<ExpenseResponseDTO> expenseDTOS = expenses.map(ExpenseMapper::toDTO);
        return ResponseEntity.ok(expenseDTOS);
    }

    @Operation(summary = "Update an expense", description = "This method is responsable to update a specific expense")
    @PatchMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> updateExpense(
            @PathVariable Integer id,
            @RequestBody @Valid ExpenseRequestDTO expenseRequestDTO) {

        Product product = getProductUseCase.execute(expenseRequestDTO.getFkProduct());
        Expense expense = ExpenseMapper.toDomain(expenseRequestDTO, product);
        Expense updatedExpense = updateExpenseUseCase.execute(expense);
        return ResponseEntity.ok(ExpenseMapper.toDTO(updatedExpense));
    }

    @Operation(summary = "Delete an expense", description = "Endpoint responsable to delete a expense")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable Integer id) {
        deleteExpenseUseCase.execute(id);
        return ResponseEntity.ok().build();
    }

}
