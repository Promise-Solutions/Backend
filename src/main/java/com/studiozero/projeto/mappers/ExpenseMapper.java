package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.ExpenseRequestDTO;
import com.studiozero.projeto.dtos.response.ExpenseResponseDTO;
import com.studiozero.projeto.entities.Expense;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseMapper {
    public static Expense toEntity(ExpenseRequestDTO dto){
        Expense expense = new Expense();

        expense.setDate((dto.getDate()));
        expense.setExpenseCategory(dto.getExpenseCategory());
        expense.setDescription((dto.getDescription()));
        expense.setAmountSpend(dto.getAmountExpend());
        expense.setQuantity(dto.getQuantity());
        expense.setPaymentType(dto.getPaymentType());
//        expense.setEmployee(dto.getEmployee);

        return expense;
    }

    public static ExpenseResponseDTO toDTO(Expense expense){
        ExpenseResponseDTO dto = new ExpenseResponseDTO();
        dto.setDate(expense.getDate());
        dto.setExpenseCategory(expense.getExpenseCategory());
        dto.setDescription(expense.getDescription());
        dto.setAmountSpend(expense.getAmountSpend());
        dto.setQuantity(expense.getQuantity());
        dto.setPaymentType(expense.getPaymentType());
        dto.setEmployee(expense.getEmployee());

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



}
