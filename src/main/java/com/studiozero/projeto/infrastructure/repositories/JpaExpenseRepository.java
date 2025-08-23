package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.infrastructure.entities.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaExpenseRepository extends JpaRepository<ExpenseEntity, Integer> {
}
