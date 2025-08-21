package com.studiozero.projeto.application.usecases.expense;

import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.repositories.ExpenseRepository;
import java.util.List;

public class ListExpensesUseCase {
    private final ExpenseRepository expenseRepository;

    public ListExpensesUseCase(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> execute() {
        return expenseRepository.listAll();
    }
}
