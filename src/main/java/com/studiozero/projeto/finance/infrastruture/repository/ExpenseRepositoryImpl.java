package com.studiozero.projeto.finance.infrastruture.repository;

import com.studiozero.projeto.finance.domain.Expense;
import com.studiozero.projeto.finance.domain.ExpenseRepository;
import com.studiozero.projeto.finance.infrastruture.entity.ExpenseEntity;
import com.studiozero.projeto.finance.infrastruture.mapper.ExpenseEntityMapper;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {
    private final JpaExpenseRepository jpaExpenseRepository;

    public ExpenseRepositoryImpl(JpaExpenseRepository jpaExpenseRepository) {
        this.jpaExpenseRepository = jpaExpenseRepository;
    }

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
