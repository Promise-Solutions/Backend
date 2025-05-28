package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.ExpenseRequestDTO;
import com.studiozero.projeto.dtos.response.ExpenseResponseDTO;
import com.studiozero.projeto.entities.Expense;
import com.studiozero.projeto.entities.Product;
import com.studiozero.projeto.repositories.ExpenseRepository;
import com.studiozero.projeto.repositories.ProductRepository;
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
        dto.setDate(expense.getDate());
        dto.setExpenseCategory(expense.getExpenseCategory());
        dto.setDescription(expense.getDescription());
        if(expense.getProduct() != null) {
            dto.setQuantity(expense.getProduct().getQuantity());
        }
        dto.setPaymentType(expense.getPaymentType());

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

        if (dto.getFkProduct() != null) {
            Product product = productRepository.findById(dto.getFkProduct()).orElse(null);
            expense.setProduct(product);
        }

        expense.setDate(dto.getDate());
        expense.setExpenseCategory(dto.getExpenseCategory());
        expense.setPaymentType(dto.getPaymentType());
        expense.setDescription(dto.getDescription());
        expense.setAmountSpend(dto.getAmountSpend());

        return expense;
    }


    public Expense toEntity(ExpenseRequestDTO dto, Integer id) {
        if (dto.getFkProduct() == null) {
           return null;
        }

        Expense expense = new Expense();


        if (dto.getFkProduct() != null ){
            Product product = productRepository.findById(dto.getFkProduct()).orElse(null);
            expense.setProduct(product);
        }
        Product product = productRepository.findById(dto.getFkProduct()).orElse(null);
        expense.setId(id);
        expense.setProduct(product);
        expense.setDate(dto.getDate());
        expense.setExpenseCategory(dto.getExpenseCategory());
        expense.setPaymentType(dto.getPaymentType());
        expense.setDescription(dto.getDescription());
        expense.setAmountSpend(dto.getAmountSpend());

        return expense;
    }


}
