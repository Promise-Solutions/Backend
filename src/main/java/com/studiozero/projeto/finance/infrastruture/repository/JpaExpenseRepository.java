package com.studiozero.projeto.finance.infrastruture.repository;

import com.studiozero.projeto.finance.infrastruture.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaExpenseRepository extends JpaRepository<ExpenseEntity, Integer> {
}
