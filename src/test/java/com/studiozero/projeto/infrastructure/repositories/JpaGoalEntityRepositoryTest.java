package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.infrastructure.entities.GoalEntity;
import com.studiozero.projeto.infrastructure.mappers.GoalEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.Implements.GoalRepositoryImpl;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaGoalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JpaGoalEntityRepositoryTest {

    private JpaGoalRepository jpaGoalRepository;
    private GoalRepositoryImpl goalRepository;

    @BeforeEach
    void setUp() {
        jpaGoalRepository = mock(JpaGoalRepository.class);
        goalRepository = new GoalRepositoryImpl(jpaGoalRepository);
    }

    @Test
    void testFindByIdSuccess() {
        Integer id = 1;
        GoalEntity entity = mock(GoalEntity.class);
        Goal goal = mock(Goal.class);

        when(jpaGoalRepository.findById(id)).thenReturn(Optional.of(entity));
        try (MockedStatic<GoalEntityMapper> mocked = mockStatic(GoalEntityMapper.class)) {
            mocked.when(() -> GoalEntityMapper.toDomain(entity)).thenReturn(goal);

            Goal result = goalRepository.findById(id);
            assertEquals(goal, result);
        }
    }

    @Test
    void testFindByIdNotFound() {
        Integer id = 1;
        when(jpaGoalRepository.findById(id)).thenReturn(Optional.empty());

        Goal result = goalRepository.findById(id);
        assertNull(result);
    }

    @Test
    void testFindTopByOrderByIdDesc() {
        GoalEntity entity1 = mock(GoalEntity.class);
        GoalEntity entity2 = mock(GoalEntity.class);
        Goal goal1 = mock(Goal.class);
        Goal goal2 = mock(Goal.class);

        when(jpaGoalRepository.findAll()).thenReturn(List.of(entity1, entity2));
        when(goal1.getId()).thenReturn(1);
        when(goal2.getId()).thenReturn(2);

        try (MockedStatic<GoalEntityMapper> mocked = mockStatic(GoalEntityMapper.class)) {
            mocked.when(() -> GoalEntityMapper.toDomain(entity1)).thenReturn(goal1);
            mocked.when(() -> GoalEntityMapper.toDomain(entity2)).thenReturn(goal2);

            Optional<Goal> result = goalRepository.findTopByOrderByIdDesc();
            assertTrue(result.isPresent());
            assertEquals(goal2, result.get());
        }
    }

    @Test
    void testFindTopByOrderByIdDescEmpty() {
        when(jpaGoalRepository.findAll()).thenReturn(List.of());

        Optional<Goal> result = goalRepository.findTopByOrderByIdDesc();
        assertTrue(result.isEmpty());
    }

    @Test
    void testSave() {
        Goal goal = mock(Goal.class);
        GoalEntity entity = mock(GoalEntity.class);

        try (MockedStatic<GoalEntityMapper> mocked = mockStatic(GoalEntityMapper.class)) {
            mocked.when(() -> GoalEntityMapper.toEntity(goal)).thenReturn(entity);

            goalRepository.save(goal);
            verify(jpaGoalRepository).save(entity);
        }
    }

    @Test
    void testDeleteById() {
        Integer id = 1;
        goalRepository.deleteById(id);
        verify(jpaGoalRepository).deleteById(id);
    }

    @Test
    void testFindAll() {
        GoalEntity entity = mock(GoalEntity.class);
        Goal goal = mock(Goal.class);

        when(jpaGoalRepository.findAll()).thenReturn(List.of(entity));
        try (MockedStatic<GoalEntityMapper> mocked = mockStatic(GoalEntityMapper.class)) {
            mocked.when(() -> GoalEntityMapper.toDomain(entity)).thenReturn(goal);

            List<Goal> result = goalRepository.findAll();
            assertEquals(1, result.size());
            assertEquals(goal, result.get(0));
        }
    }
}