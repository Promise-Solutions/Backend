package com.studiozero.projeto.finance.domain;

import java.util.List;

public interface ExpenseRepository {
    Expense findById(Integer id);

    void save(Expense expense);

    void deleteById(Integer id);

    List<Expense> findAll();
}
