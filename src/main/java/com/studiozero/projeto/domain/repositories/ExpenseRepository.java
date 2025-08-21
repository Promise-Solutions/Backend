package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Expense;

import java.util.List;

public interface ExpenseRepository {
    Expense findById(Integer id);

    void save(Expense expense);

    void deleteById(Integer id);

    List<Expense> listAll();
}
