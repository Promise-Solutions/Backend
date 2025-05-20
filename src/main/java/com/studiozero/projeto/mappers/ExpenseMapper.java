package com.studiozero.projeto.mappers;

import com.studiozero.projeto.dtos.request.ExpenseRequestDTO;
import com.studiozero.projeto.dtos.response.ExpenseResponseDTO;
import com.studiozero.projeto.entities.Client;
import com.studiozero.projeto.entities.Expense;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseMapper {
    public static Expense toEntity(ExpenseRequestDTO dto){
        Expense expense = new Expense();

        if (dto.getFkClient() != null) {
            Client client = clientRepository.findById(dto.getFkClient()).orElse(null);
            command.setClient(client);

        }

        expense.setDate((dto.getDate()));
        expense.setExpenseCategory(dto.getExpenseCategory());
        expense.setDescription((dto.getDescription()));
        expense.setAmountSpend(dto.getAmountExpend());
        expense.setPaymentType(dto.getPaymentType());
        expense.setProduct(dto.getIdProduto());
        return expense;
    }

    public static ExpenseResponseDTO toDTO(Expense expense){
        ExpenseResponseDTO dto = new ExpenseResponseDTO();
        dto.setDate(expense.getDate());
        dto.setExpenseCategory(expense.getExpenseCategory());
        dto.setDescription(expense.getDescription());
        dto.setAmountSpend(expense.getAmountSpend());
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



}
