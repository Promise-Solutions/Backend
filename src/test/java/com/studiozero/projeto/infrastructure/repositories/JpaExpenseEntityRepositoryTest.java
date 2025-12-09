package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Expense;
import com.studiozero.projeto.infrastructure.entities.ExpenseEntity;
import com.studiozero.projeto.infrastructure.mappers.ExpenseEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.Implements.ExpenseRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JpaExpenseEntityRepositoryTest {

    private JpaExpenseRepository jpaExpenseRepository;
    private ExpenseRepositoryImpl expenseRepository;

    @BeforeEach
    void setUp() {
        jpaExpenseRepository = mock(JpaExpenseRepository.class);
        expenseRepository = new ExpenseRepositoryImpl(jpaExpenseRepository);
    }

    @Test
    void testFindByIdSuccess() {
        Integer id = 1;
        ExpenseEntity entity = mock(ExpenseEntity.class);
        Expense expense = mock(Expense.class);

        when(jpaExpenseRepository.findById(id)).thenReturn(Optional.of(entity));
        try (MockedStatic<ExpenseEntityMapper> mocked = mockStatic(ExpenseEntityMapper.class)) {
            mocked.when(() -> ExpenseEntityMapper.toDomain(entity)).thenReturn(expense);

            Expense result = expenseRepository.findById(id);
            assertEquals(expense, result);
        }
    }

    @Test
    void testFindByIdNotFound() {
        Integer id = 1;
        when(jpaExpenseRepository.findById(id)).thenReturn(Optional.empty());

        Expense result = expenseRepository.findById(id);
        assertNull(result);
    }

    @Test
    void testSave() {
        Expense expense = mock(Expense.class);
        ExpenseEntity entity = mock(ExpenseEntity.class);

        try (MockedStatic<ExpenseEntityMapper> mocked = mockStatic(ExpenseEntityMapper.class)) {
            mocked.when(() -> ExpenseEntityMapper.toEntity(expense)).thenReturn(entity);

            expenseRepository.save(expense);
            verify(jpaExpenseRepository).save(entity);
        }
    }

    @Test
    void testDeleteById() {
        Integer id = 1;
        expenseRepository.deleteById(id);
        verify(jpaExpenseRepository).deleteById(id);
    }
}