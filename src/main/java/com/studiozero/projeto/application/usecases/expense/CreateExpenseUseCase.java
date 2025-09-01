package com.studiozero.projeto.application.usecases.expense;

import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.repositories.ExpenseRepository;

public class CreateExpenseUseCase {
    private final ExpenseRepository expenseRepository;

    public CreateExpenseUseCase(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense execute(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Despesa inválida");
        }
        expenseRepository.save(expense);
        return expense;
    }
}
