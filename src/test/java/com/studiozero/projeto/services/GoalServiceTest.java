package com.studiozero.projeto.services;

import com.studiozero.projeto.entities.Goal;
import com.studiozero.projeto.enums.Context;
import com.studiozero.projeto.exceptions.NotFoundException;
import com.studiozero.projeto.repositories.GoalRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoalServiceTest {

    @InjectMocks
    private GoalService goalService;

    @Mock
    private GoalRepository goalRepository;

    @Mock
    private TracingService tracingService;

    private Goal goal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        goal = new Goal();
        goal.setId(1);
        goal.setName("Increase Revenue");
        goal.setTargetValue(10000.0);
    }

    @Test
    @DisplayName("Should create goal successfully")
    void createGoal_Success() {
        when(goalRepository.save(any(Goal.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Goal savedGoal = goalService.createGoal(goal);

        assertEquals(goal, savedGoal);
        verify(goalRepository).save(goal);
    }

    @Test
    @DisplayName("Should find goal by ID successfully")
    void findGoalById_Success() {
        when(goalRepository.findById(goal.getId())).thenReturn(Optional.of(goal));

        Goal found = goalService.findGoalById(goal.getId());

        assertEquals(goal, found);
    }

    @Test
    @DisplayName("Should throw NotFoundException when goal ID does not exist")
    void findGoalById_NotFound() {
        when(goalRepository.findById(goal.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> goalService.findGoalById(goal.getId()));
    }

    @Test
    @DisplayName("Should return list of all goals")
    void listGoals_ReturnsList() {
        List<Goal> goals = Arrays.asList(goal, goal);
        when(goalRepository.findAll()).thenReturn(goals);

        List<Goal> result = goalService.listGoals();

        assertEquals(2, result.size());
        assertEquals(goals, result);
    }

    @Test
    @DisplayName("Should find most recent goal successfully")
    void findMostRecentGoal_Success() {
        when(goalRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(goal));

        Goal found = goalService.findMostRecentGoal();

        assertEquals(goal, found);
    }

    @Test
    @DisplayName("Should throw NotFoundException when no most recent goal is found")
    void findMostRecentGoal_NotFound() {
        when(goalRepository.findTopByOrderByIdDesc()).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> goalService.findMostRecentGoal());
    }

    @Test
    @DisplayName("Should update goal successfully when goal exists")
    void updateGoal_Success() {
        when(goalRepository.existsById(goal.getId())).thenReturn(true);
        when(goalRepository.save(any(Goal.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(tracingService.setTracing(Context.EXPENSE)).thenReturn(null);

        Goal updated = goalService.updateGoal(goal);

        assertEquals(goal.getId(), updated.getId());
        verify(tracingService).setTracing(Context.EXPENSE);
        verify(goalRepository).save(goal);
    }

    @Test
    @DisplayName("Should create goal when updating and ID is null")
    void updateGoal_CreateWhenIdIsNull() {
        goal.setId(null);
        when(goalRepository.save(any(Goal.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Goal created = goalService.updateGoal(goal);

        assertNotNull(created);
        verify(goalRepository).save(goal);
    }

    @Test
    @DisplayName("Should throw NotFoundException when updating non-existing goal")
    void updateGoal_NotFound() {
        when(goalRepository.existsById(goal.getId())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> goalService.updateGoal(goal));
        verify(goalRepository, never()).save(any());
        verify(tracingService, never()).setTracing(any());
    }

    @Test
    @DisplayName("Should delete goal successfully when goal exists")
    void deleteGoal_Success() {
        when(goalRepository.existsById(goal.getId())).thenReturn(true);
        when(tracingService.setTracing(Context.EXPENSE)).thenReturn(null);

        goalService.deleteGoal(goal.getId());

        verify(tracingService).setTracing(Context.EXPENSE);
        verify(goalRepository).deleteById(goal.getId());
    }

    @Test
    @DisplayName("Should throw NotFoundException when deleting non-existing goal")
    void deleteGoal_NotFound() {
        when(goalRepository.existsById(goal.getId())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> goalService.deleteGoal(goal.getId()));

        verify(goalRepository, never()).deleteById(any());
        verify(tracingService, never()).setTracing(any());
    }
}
