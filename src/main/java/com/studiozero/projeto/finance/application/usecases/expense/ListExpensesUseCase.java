package com.studiozero.projeto.finance.application.usecases.expense;

import com.studiozero.projeto.finance.domain.Expense;
import com.studiozero.projeto.finance.domain.ExpenseRepository;
import java.util.List;

public class ListExpensesUseCase {
    private final ExpenseRepository expenseRepository;

    public ListExpensesUseCase(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> execute() {
        return expenseRepository.findAll();
    }
}
