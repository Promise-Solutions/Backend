package com.studiozero.projeto.infrastructure.mappers;

import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.infrastructure.entities.ExpenseEntity;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.infrastructure.entities.ProductEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ExpenseEntityMapper {
    public static Expense toDomain(ExpenseEntity entity) {
        if (entity == null) return null;
        return new Expense(
            entity.getId(),
            entity.getDate(),
            entity.getExpenseCategory(),
            entity.getDescription(),
            entity.getQuantity(),
            entity.getAmountSpend(),
            ProductEntityMapper.toDomain(entity.getProduct()),
            entity.getPaymentType()
        );
    }

    public static ExpenseEntity toEntity(Expense expense) {
        if (expense == null) return null;
        return new ExpenseEntity(
            expense.getId(),
            expense.getDate(),
            expense.getExpenseCategory(),
            expense.getDescription(),
            expense.getQuantity(),
            expense.getAmountSpend(),
            ProductEntityMapper.toEntity(expense.getProduct()),
            expense.getPaymentType()
        );
    }

    public static List<Expense> toDomainList(List<ExpenseEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(ExpenseEntityMapper::toDomain).toList();
    }

    public static List<ExpenseEntity> toEntityList(List<Expense> expenses) {
        if (expenses == null) return null;
        return expenses.stream().map(ExpenseEntityMapper::toEntity).toList();
    }
}
