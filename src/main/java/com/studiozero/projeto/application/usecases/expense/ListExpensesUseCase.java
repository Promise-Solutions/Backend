package com.studiozero.projeto.application.usecases.expense;

import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.repositories.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ListExpensesUseCase {
    private final ExpenseRepository expenseRepository;

    public ListExpensesUseCase(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Page<Expense> execute(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }
}
