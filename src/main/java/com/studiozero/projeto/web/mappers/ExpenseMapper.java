package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.application.dtos.request.ExpenseRequestDTO;
import com.studiozero.projeto.application.dtos.response.ExpenseResponseDTO;
import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.application.enums.ExpenseCategory;
import com.studiozero.projeto.infrastructure.exceptions.NotFoundException;
import com.studiozero.projeto.domain.repositories.ExpenseRepository;
import com.studiozero.projeto.domain.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseMapper {
    private final ProductRepository productRepository;

    public ExpenseMapper(ProductRepository productRepository, ExpenseRepository expenseRepository) {
        this.productRepository = productRepository;
    }

    public static ExpenseResponseDTO toDTO(Expense expense){
        ExpenseResponseDTO dto = new ExpenseResponseDTO();
        dto.setId(expense.getId());
        dto.setDate(expense.getDate());
        dto.setExpenseCategory(expense.getExpenseCategory());
        dto.setDescription(expense.getDescription());
        dto.setAmountSpend(expense.getAmountSpend());
        dto.setPaymentType(expense.getPaymentType());

        if(expense.getProduct() != null) {
            dto.setFkProduct(expense.getProduct().getId());
        }
        if(expense.getQuantity() != null) {
            dto.setQuantity(expense.getQuantity());
        }

        return dto;
    }

    public static List<ExpenseResponseDTO> toListDtos (List<Expense> entity){
        if (entity == null) {
            return null;
        }

        return entity.stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }

    public Expense toEntity(ExpenseRequestDTO dto) {
        Expense expense = new Expense();

        if (dto.getExpenseCategory() == ExpenseCategory.STOCK && dto.getFkProduct() != null) {
            Product product = productRepository.findById(dto.getFkProduct()).orElseThrow(() -> new NotFoundException("Produto não encontrado!"));
            expense.setProduct(product);
        }
        if(dto.getQuantity() != null) {
            expense.setQuantity(dto.getQuantity());
        }

        expense.setDate(dto.getDate());
        expense.setExpenseCategory(dto.getExpenseCategory());
        expense.setPaymentType(dto.getPaymentType());
        expense.setDescription(dto.getDescription());
        expense.setAmountSpend(dto.getAmountSpend());

        return expense;
    }

    public Expense toEntity(ExpenseRequestDTO dto, Integer id) {
        Expense expense = new Expense();

        if (dto.getExpenseCategory() == ExpenseCategory.STOCK && dto.getFkProduct() != null) {
            Product product = productRepository.findById(dto.getFkProduct()).orElseThrow(() -> new NotFoundException("Produto não encontrado!"));
            expense.setProduct(product);
        }
        if(dto.getQuantity() != null) {
            expense.setQuantity(dto.getQuantity());
        }

        expense.setId(id);
        expense.setDate(dto.getDate());
        expense.setExpenseCategory(dto.getExpenseCategory());
        expense.setPaymentType(dto.getPaymentType());
        expense.setDescription(dto.getDescription());
        expense.setAmountSpend(dto.getAmountSpend());

        return expense;
    }
}
