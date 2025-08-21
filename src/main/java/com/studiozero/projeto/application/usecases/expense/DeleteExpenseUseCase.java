package com.studiozero.projeto.application.usecases.expense;


import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.repositories.ExpenseRepository;

public class DeleteExpenseUseCase {
    private final ExpenseRepository expenseRepository;

    public DeleteExpenseUseCase(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void execute(Integer id) {
        Expense expense = expenseRepository.findById(id);
        if (expense == null) {
            throw new IllegalArgumentException("Despesa não encontrada");
        }
        expenseRepository.deleteById(id);
    }
}
