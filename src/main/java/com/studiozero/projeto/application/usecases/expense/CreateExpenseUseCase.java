package com.studiozero.projeto.application.usecases.expense;

import com.studiozero.projeto.application.enums.ExpenseCategory;
import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.domain.repositories.ExpenseRepository;
import com.studiozero.projeto.domain.repositories.ProductRepository;
import com.studiozero.projeto.web.dtos.request.ExpenseRequestDTO;

public class CreateExpenseUseCase {
    private final ExpenseRepository expenseRepository;
    private final ProductRepository productRepository;

    public CreateExpenseUseCase(ExpenseRepository expenseRepository, ProductRepository productRepository) {
        this.expenseRepository = expenseRepository;
        this.productRepository = productRepository;
    }

    public Expense execute(ExpenseRequestDTO expenseDto) {
        if (expenseDto == null) {
            throw new IllegalArgumentException("Despesa inválida");
        }

        Product product = null;

        if (expenseDto.getFkProduct() != null && expenseDto.getExpenseCategory() == ExpenseCategory.STOCK) {
            product = productRepository.findById(expenseDto.getFkProduct());

            if(product == null) throw new IllegalArgumentException("Produto não encontrado para o registro da despesa");

            product.increaseQuantity(expenseDto.getQuantity());
            productRepository.save(product);
        }

        Expense newExpense = new Expense(null,
                expenseDto.getDate(),
                expenseDto.getExpenseCategory(),
                expenseDto.getDescription(),
                expenseDto.getQuantity(),
                expenseDto.getAmountSpend(),
                product,
                expenseDto.getPaymentType()
        );

        expenseRepository.save(newExpense);
        return newExpense;
    }
}
