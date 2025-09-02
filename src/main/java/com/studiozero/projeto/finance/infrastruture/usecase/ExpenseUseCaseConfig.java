package com.studiozero.projeto.finance.infrastruture.usecase;

import com.studiozero.projeto.finance.infrastruture.repository.ExpenseRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.finance.application.usecases.expense.CreateExpenseUseCase;
import com.studiozero.projeto.finance.application.usecases.expense.DeleteExpenseUseCase;
import com.studiozero.projeto.finance.application.usecases.expense.GetExpenseUseCase;
import com.studiozero.projeto.finance.application.usecases.expense.ListExpensesUseCase;
import com.studiozero.projeto.finance.application.usecases.expense.UpdateExpenseUseCase;

@Configuration
public class ExpenseUseCaseConfig {
    @Bean
    public CreateExpenseUseCase createExpenseUseCase(ExpenseRepositoryImpl expenseRepository) {
        return new CreateExpenseUseCase(expenseRepository);
    }

    @Bean
    public DeleteExpenseUseCase deleteExpenseUseCase(ExpenseRepositoryImpl expenseRepository) {
        return new DeleteExpenseUseCase(expenseRepository);
    }

    @Bean
    public GetExpenseUseCase getExpenseUseCase(ExpenseRepositoryImpl expenseRepository) {
        return new GetExpenseUseCase(expenseRepository);
    }

    @Bean
    public ListExpensesUseCase listExpensesUseCase(ExpenseRepositoryImpl expenseRepository) {
        return new ListExpensesUseCase(expenseRepository);
    }

    @Bean
    public UpdateExpenseUseCase updateExpenseUseCase(ExpenseRepositoryImpl expenseRepository) {
        return new UpdateExpenseUseCase(expenseRepository);
    }
}

