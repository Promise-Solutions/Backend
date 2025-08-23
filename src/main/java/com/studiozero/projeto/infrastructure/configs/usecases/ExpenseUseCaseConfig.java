package com.studiozero.projeto.infrastructure.configs.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.domain.repositories.ExpenseRepository;
import com.studiozero.projeto.application.usecases.expense.CreateExpenseUseCase;
import com.studiozero.projeto.application.usecases.expense.DeleteExpenseUseCase;
import com.studiozero.projeto.application.usecases.expense.GetExpenseUseCase;
import com.studiozero.projeto.application.usecases.expense.ListExpensesUseCase;
import com.studiozero.projeto.application.usecases.expense.UpdateExpenseUseCase;

@Configuration
public class ExpenseUseCaseConfig {
    @Bean
    public CreateExpenseUseCase createExpenseUseCase(ExpenseRepository expenseRepository) {
        return new CreateExpenseUseCase(expenseRepository);
    }

    @Bean
    public DeleteExpenseUseCase deleteExpenseUseCase(ExpenseRepository expenseRepository) {
        return new DeleteExpenseUseCase(expenseRepository);
    }

    @Bean
    public GetExpenseUseCase getExpenseUseCase(ExpenseRepository expenseRepository) {
        return new GetExpenseUseCase(expenseRepository);
    }

    @Bean
    public ListExpensesUseCase listExpensesUseCase(ExpenseRepository expenseRepository) {
        return new ListExpensesUseCase(expenseRepository);
    }

    @Bean
    public UpdateExpenseUseCase updateExpenseUseCase(ExpenseRepository expenseRepository) {
        return new UpdateExpenseUseCase(expenseRepository);
    }
}

