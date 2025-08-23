package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.repositories.ExpenseRepository;
import com.studiozero.projeto.infrastructure.entities.ExpenseEntity;
import com.studiozero.projeto.infrastructure.mappers.ExpenseEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaExpenseRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ExpenseRepositoryImpl implements ExpenseRepository {
    private final JpaExpenseRepository jpaExpenseRepository;

    @Override
    public Expense findById(Integer id) {
        return jpaExpenseRepository.findById(id)
            .map(ExpenseEntityMapper::toDomain)
            .orElse(null);
    }

    @Override
    public void save(Expense expense) {
        ExpenseEntity entity = ExpenseEntityMapper.toEntity(expense);
        jpaExpenseRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        jpaExpenseRepository.deleteById(id);
    }

    @Override
    public List<Expense> findAll() {
        return jpaExpenseRepository.findAll()
            .stream()
            .map(ExpenseEntityMapper::toDomain)
            .toList();
    }
}
