package com.studiozero.projeto.application.usecases.expense;

import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.repositories.ExpenseRepository;

public class UpdateExpenseUseCase {
    private final ExpenseRepository expenseRepository;

    public UpdateExpenseUseCase(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense execute(Expense expense) {
        if (expense == null || expense.getId() == null || expense.getId().toString().isEmpty()) {
            throw new IllegalArgumentException("Despesa inválida");
        }
        Expense existing = expenseRepository.findById(expense.getId());
        if (existing == null) {
            throw new IllegalArgumentException("Despesa não encontrada");
        }
        expenseRepository.save(expense);
        return expense;
    }
}
