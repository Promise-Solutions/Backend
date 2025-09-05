package com.studiozero.projeto.application.usecases.expense;

import com.studiozero.projeto.application.enums.ExpenseCategory;
import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.application.usecases.product.UpdateProductUseCase;
import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.domain.repositories.ExpenseRepository;

public class CreateExpenseUseCase {
    private final ExpenseRepository expenseRepository;
    private final UpdateProductUseCase updateProductUseCase;
    private final GetProductUseCase getProductUseCase;

    public CreateExpenseUseCase(ExpenseRepository expenseRepository, UpdateProductUseCase updateProductUseCase, GetProductUseCase getProductUseCase) {
        this.expenseRepository = expenseRepository;
        this.updateProductUseCase = updateProductUseCase;
        this.getProductUseCase = getProductUseCase;
    }

    public Expense execute(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Despesa inválida");
        }

        if (expense.getProduct() != null && expense.getExpenseCategory() == ExpenseCategory.STOCK) {
            Product product = getProductUseCase.execute(expense.getProduct().getId());
            product.setQuantity(expense.getQuantity() + product.getQuantity());
            updateProductUseCase.execute(product);
        }

        expenseRepository.save(expense);
        return expense;
    }
}
