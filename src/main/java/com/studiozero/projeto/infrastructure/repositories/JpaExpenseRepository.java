package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaExpenseRepository extends JpaRepository<Expense, Integer> {
}
