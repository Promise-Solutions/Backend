package com.studiozero.projeto.finance.web.mapper;

import com.studiozero.projeto.finance.domain.Expense;
import com.studiozero.projeto.finance.web.dto.ExpenseRequestDTO;
import com.studiozero.projeto.finance.web.dto.ExpenseResponseDTO;
import com.studiozero.projeto.catalog.domain.Product;
import java.util.List;

public class ExpenseMapper {
    public static Expense toDomain(ExpenseRequestDTO dto, Product product) {
        if (dto == null) return null;
        return new Expense(
            null,
            dto.getDate(),
            dto.getExpenseCategory(),
            dto.getDescription(),
            dto.getQuantity(),
            dto.getAmountSpend(),
            product,
            dto.getPaymentType()
        );
    }

    public static ExpenseResponseDTO toDTO(Expense expense) {
        if (expense == null) return null;
        ExpenseResponseDTO dto = new ExpenseResponseDTO();
        dto.setId(expense.getId());
        dto.setDate(expense.getDate());
        dto.setExpenseCategory(expense.getExpenseCategory());
        dto.setDescription(expense.getDescription());
        dto.setAmountSpend(expense.getAmountSpend());
        dto.setQuantity(expense.getQuantity());
        dto.setPaymentType(expense.getPaymentType());
        dto.setFkProduct(expense.getProduct() != null ? expense.getProduct().getId() : null);
        return dto;
    }

    public static List<ExpenseResponseDTO> toDTOList(List<Expense> entities) {
        if (entities == null) return null;
        return entities.stream().map(ExpenseMapper::toDTO).toList();
    }
}
