package com.studiozero.projeto.infrastructure.repositories.jpa;

import com.studiozero.projeto.infrastructure.entities.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaExpenseRepository extends JpaRepository<ExpenseEntity, Integer> {
}
