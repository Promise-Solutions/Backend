package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.domain.repositories.ExpenseRepository;
import com.studiozero.projeto.infrastructure.repositories.JpaExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {
    private final JpaExpenseRepository jpaExpenseRepository;

    @Autowired
    public ExpenseRepositoryImpl(JpaExpenseRepository jpaExpenseRepository) {
        this.jpaExpenseRepository = jpaExpenseRepository;
    }

    @Override
    public Expense findById(Integer id) {
        return jpaExpenseRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Expense expense) {
        jpaExpenseRepository.save(expense);
    }

    @Override
    public void deleteById(Integer id) {
        jpaExpenseRepository.deleteById(id);
    }

    @Override
    public List<Expense> listAll() {
        return jpaExpenseRepository.findAll();
    }
}
