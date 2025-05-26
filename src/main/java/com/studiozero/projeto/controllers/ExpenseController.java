package com.studiozero.projeto.controllers;

import com.studiozero.projeto.dtos.request.ExpenseRequestDTO;
import com.studiozero.projeto.dtos.response.ExpenseResponseDTO;
import com.studiozero.projeto.entities.Expense;
import com.studiozero.projeto.mappers.ExpenseMapper;
import com.studiozero.projeto.services.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
@Tag(name = "Expenses", description = "Endpoints for Expenses Management")

public class ExpenseController {

    private final ExpenseMapper expenseMapper;
    private final ExpenseService expenseService;

    @Operation(
            summary = "Create a new Expense", description = "This endpoint is resposable to create a new expense"
    )
    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> createExpense(@RequestBody @Valid ExpenseRequestDTO expenseDTO) {
        Expense savedExpense = expenseService.createExpense(expenseDTO);

        return ResponseEntity.status(201).body(ExpenseMapper.toDTO(savedExpense));
    }

    @Operation(
            summary = "Search for an Expense", description = "This endpoint is resposable to return a specific expense"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> findExpenseById(@PathVariable Integer id) {
        Expense expense = expenseService.findExpenseById(id);

        return ResponseEntity.ok(ExpenseMapper.toDTO(expense));
    }

    @Operation(
            summary = "List all expenses", description = "This endpoint will listing all expenses."
    )
    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> listAllExpenses() {

        List<Expense> expenses = new ArrayList<>();

        if (expenses.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<ExpenseResponseDTO> expenseDTOS = ExpenseMapper.toListDtos(expenses);

        return ResponseEntity.status(200).body(expenseDTOS);

    }


    @Operation(
            summary = "Update an expense", description = "This method is responsable to update a specific expense"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> updateExpense(@PathVariable Integer id, @RequestBody @Valid ExpenseRequestDTO expenseRequestDTO){

        Expense expense = expenseMapper.toEntity(expenseRequestDTO, id);
        Expense updatedExpense = expenseService.updateExpense(expense);

        return ResponseEntity.ok(ExpenseMapper.toDTO(updatedExpense));
    }

    @Operation(
            summary = "Delete an expense",
            description = "Endpoint responsable to delete a expense"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable Integer id
    ){
        expenseService
    }



}
