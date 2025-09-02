package com.studiozero.projeto.finance.application.usecases.expense;


import com.studiozero.projeto.finance.domain.Expense;
import com.studiozero.projeto.finance.domain.ExpenseRepository;

public class GetExpenseUseCase {
    private final ExpenseRepository expenseRepository;

    public GetExpenseUseCase(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense execute(Integer id) {
        Expense expense = expenseRepository.findById(id);
        if (expense == null) {
            throw new IllegalArgumentException("Despesa não encontrada");
        }
        return expense;
    }
}
