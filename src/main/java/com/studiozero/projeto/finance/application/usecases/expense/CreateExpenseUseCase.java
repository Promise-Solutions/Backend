package com.studiozero.projeto.finance.application.usecases.expense;

import com.studiozero.projeto.finance.domain.Expense;
import com.studiozero.projeto.finance.domain.ExpenseRepository;

public class CreateExpenseUseCase {
    private final ExpenseRepository expenseRepository;

    public CreateExpenseUseCase(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense execute(Expense expense) {
        if (expense == null || expense.getId() == null || expense.getId().toString().isEmpty()) {
            throw new IllegalArgumentException("Despesa inválida");
        }
        expenseRepository.save(expense);
        return expense;
    }
}
