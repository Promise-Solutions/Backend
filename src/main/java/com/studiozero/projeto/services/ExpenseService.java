package com.studiozero.projeto.services;

import com.studiozero.projeto.dtos.request.ExpenseRequestDTO;
import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.entities.Expense;
import com.studiozero.projeto.entities.Product;
import com.studiozero.projeto.enums.ExpenseCategory;
import com.studiozero.projeto.exceptions.ConflictException;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.mappers.ExpenseMapper;
import com.studiozero.projeto.repositories.ExpenseRepository;
import com.studiozero.projeto.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ProductService productService;
    private final ExpenseRepository expenseRepository;
    private ExpenseMapper expenseMapper;

    public Expense createExpense(ExpenseRequestDTO dto) {
        // SETAR NO EXPENSE

        Expense expense = expenseMapper.toEntity(dto);

        if (dto.getExpenseCategory() == ExpenseCategory.STOCK) {
            Product product = productService.findProductById(dto.getFkProduct());
            product.setQuantity(dto.getQuantity());
        }

        return expenseRepository.save(expense);
    }

    public List<Expense> expenseList() {
        return expenseRepository.findAll();
    }

    public Expense findExpenseById(Integer id) {
        return expenseRepository.findById(id).orElseThrow(() -> new NotFoundException("Expense not found"));
    }

    public Expense updateExpense(Expense expense) {
        if (expenseRepository.existsById(expense.getId())) {
            expense.setId(expense.getId());
            return expenseRepository.save(expense);
        }
        throw new NotFoundException("Expense not found");
    }

    public void deleteExpense(Integer id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
        } else {
            throw new NotFoundException("Expense not found");
        }
    }


    /**
     * TOD0:
     *findByID
     * verificar pelo tipo
     * usar a service de produto
     */

}
