package com.studiozero.projeto.web.mappers;

import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.web.dtos.request.ExpenseRequestDTO;
import com.studiozero.projeto.web.dtos.response.ExpenseResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseMapper {

    public static ExpenseResponseDTO toDTO(Expense expense) {
        if (expense == null) {
            return null;
        }
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

    public static List<ExpenseResponseDTO> toListDtos(List<Expense> entity) {
        if (entity == null) {
            return null;
        }

        return entity.stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }

    public Expense toEntity(ExpenseRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        // id gerado pelo banco/usecase
        return new Expense(
                null,
                dto.getDate(),
                dto.getExpenseCategory(),
                dto.getDescription(),
                dto.getQuantity(),
                dto.getAmountSpend(),
                null, // product deve ser resolvido pelo service/usecase
                dto.getPaymentType());
    }

    public Expense toEntity(ExpenseRequestDTO dto, Integer id) {
        if (dto == null || id == null) {
            return null;
        }
        return new Expense(
                id,
                dto.getDate(),
                dto.getExpenseCategory(),
                dto.getDescription(),
                dto.getQuantity(),
                dto.getAmountSpend(),
                null, // product deve ser resolvido pelo service/usecase
                dto.getPaymentType());
    }
}
