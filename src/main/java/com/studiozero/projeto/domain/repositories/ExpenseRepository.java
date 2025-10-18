package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseRepository {
    Expense findById(Integer id);

    void save(Expense expense);

    void deleteById(Integer id);

    Page<Expense> findAll(Pageable pageable);
}
