package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.application.usecases.product.UpdateProductUseCase;
import com.studiozero.projeto.domain.repositories.ProductRepository;
import com.studiozero.projeto.infrastructure.repositories.Implements.ExpenseRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.application.usecases.expense.CreateExpenseUseCase;
import com.studiozero.projeto.application.usecases.expense.DeleteExpenseUseCase;
import com.studiozero.projeto.application.usecases.expense.GetExpenseUseCase;
import com.studiozero.projeto.application.usecases.expense.ListExpensesUseCase;
import com.studiozero.projeto.application.usecases.expense.UpdateExpenseUseCase;

@Configuration
public class ExpenseUseCaseConfig {
    @Bean
    public CreateExpenseUseCase createExpenseUseCase(ExpenseRepositoryImpl expenseRepository, UpdateProductUseCase updateProductUseCase, GetProductUseCase getProductUseCase) {
        return new CreateExpenseUseCase(expenseRepository, updateProductUseCase, getProductUseCase);
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
    public UpdateExpenseUseCase updateExpenseUseCase(ExpenseRepositoryImpl expenseRepository, UpdateProductUseCase updateProductUseCase, GetProductUseCase getProductUseCase) {
        return new UpdateExpenseUseCase(expenseRepository, updateProductUseCase, getProductUseCase);
    }
}

