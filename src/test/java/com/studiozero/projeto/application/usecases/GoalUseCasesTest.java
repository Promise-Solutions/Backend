package com.studiozero.projeto.application.usecases;

import com.studiozero.projeto.application.usecases.goal.*;
import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.domain.repositories.GoalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GoalUseCasesTest {

    @Mock
    private GoalRepository goalRepository;

    @Nested
    class CreateGoalUseCaseTests {
        private CreateGoalUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new CreateGoalUseCase(goalRepository);
        }

        @Test
        void shouldCreateGoalSuccessfully() {
            Goal goal = new Goal();
            goal.setId(1);

            Goal result = useCase.execute(goal);

            assertNotNull(result);
            assertEquals(1, result.getId());
            verify(goalRepository, times(1)).save(goal);
        }

        @Test
        void shouldThrowExceptionWhenGoalIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("Meta inválida", exception.getMessage());
            verify(goalRepository, never()).save(any());
        }

        @Test
        void shouldReturnSameGoalInstanceAfterSave() {
            Goal goal = new Goal();

            Goal result = useCase.execute(goal);

            assertSame(goal, result);
            verify(goalRepository, times(1)).save(goal);
        }

        @Test
        void shouldCreateGoalWithAllFields() {
            Goal goal = new Goal();
            goal.setId(1);

            Goal result = useCase.execute(goal);

            assertNotNull(result);
            verify(goalRepository, times(1)).save(goal);
        }
    }

    @Nested
    class DeleteGoalUseCaseTests {
        private DeleteGoalUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new DeleteGoalUseCase(goalRepository);
        }

        @Test
        void shouldDeleteGoalSuccessfully() {
            Integer goalId = 1;
            Goal goal = new Goal();
            goal.setId(goalId);

            when(goalRepository.findById(goalId)).thenReturn(goal);

            useCase.execute(goalId);

            verify(goalRepository, times(1)).findById(goalId);
            verify(goalRepository, times(1)).deleteById(goalId);
        }

        @Test
        void shouldThrowExceptionWhenGoalNotFound() {
            Integer goalId = 999;

            when(goalRepository.findById(goalId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(goalId));

            assertEquals("Meta não encontrada", exception.getMessage());
            verify(goalRepository, times(1)).findById(goalId);
            verify(goalRepository, never()).deleteById(any());
        }

        @Test
        void shouldCheckGoalExistenceBeforeDeleting() {
            Integer goalId = 1;

            when(goalRepository.findById(goalId)).thenReturn(null);

            assertThrows(IllegalArgumentException.class, () -> useCase.execute(goalId));

            verify(goalRepository, never()).deleteById(any());
        }
    }

    @Nested
    class GetGoalUseCaseTests {
        private GetGoalUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetGoalUseCase(goalRepository);
        }

        @Test
        void shouldReturnGoalWhenFound() {
            Integer goalId = 1;
            Goal expectedGoal = new Goal();
            expectedGoal.setId(goalId);

            when(goalRepository.findById(goalId)).thenReturn(expectedGoal);

            Goal result = useCase.execute(goalId);

            assertNotNull(result);
            assertEquals(goalId, result.getId());
            verify(goalRepository, times(1)).findById(goalId);
        }

        @Test
        void shouldThrowExceptionWhenGoalNotFound() {
            Integer goalId = 999;

            when(goalRepository.findById(goalId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(goalId));

            assertEquals("Meta não encontrada", exception.getMessage());
            verify(goalRepository, times(1)).findById(goalId);
        }

        @Test
        void shouldReturnSameGoalInstanceFromRepository() {
            Integer goalId = 1;
            Goal expectedGoal = new Goal();
            expectedGoal.setId(goalId);

            when(goalRepository.findById(goalId)).thenReturn(expectedGoal);

            Goal result = useCase.execute(goalId);

            assertSame(expectedGoal, result);
        }
    }

    @Nested
    class GetMostRecentGoalUseCaseTests {
        private GetMostRecentGoalUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetMostRecentGoalUseCase(goalRepository);
        }

        @Test
        void shouldReturnMostRecentGoalWhenExists() {
            Goal mostRecentGoal = new Goal();
            mostRecentGoal.setId(5);

            when(goalRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(mostRecentGoal));

            Goal result = useCase.execute();

            assertNotNull(result);
            assertEquals(5, result.getId());
            verify(goalRepository, times(1)).findTopByOrderByIdDesc();
        }

        @Test
        void shouldReturnNullWhenNoGoalsExist() {
            when(goalRepository.findTopByOrderByIdDesc()).thenReturn(Optional.empty());

            Goal result = useCase.execute();

            assertNull(result);
            verify(goalRepository, times(1)).findTopByOrderByIdDesc();
        }

        @Test
        void shouldReturnSameGoalInstanceFromRepository() {
            Goal mostRecentGoal = new Goal();
            mostRecentGoal.setId(10);

            when(goalRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(mostRecentGoal));

            Goal result = useCase.execute();

            assertSame(mostRecentGoal, result);
        }

        @Test
        void shouldCallRepositoryExactlyOnce() {
            when(goalRepository.findTopByOrderByIdDesc()).thenReturn(Optional.empty());

            useCase.execute();

            verify(goalRepository, times(1)).findTopByOrderByIdDesc();
            verifyNoMoreInteractions(goalRepository);
        }
    }

    @Nested
    class ListGoalsUseCaseTests {
        private ListGoalsUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new ListGoalsUseCase(goalRepository);
        }

        @Test
        void shouldReturnListOfGoals() {
            Goal goal1 = new Goal();
            goal1.setId(1);

            Goal goal2 = new Goal();
            goal2.setId(2);

            List<Goal> expectedGoals = Arrays.asList(goal1, goal2);

            when(goalRepository.findAll()).thenReturn(expectedGoals);

            List<Goal> result = useCase.execute();

            assertNotNull(result);
            assertEquals(2, result.size());
            verify(goalRepository, times(1)).findAll();
        }

        @Test
        void shouldReturnEmptyListWhenNoGoalsExist() {
            when(goalRepository.findAll()).thenReturn(Collections.emptyList());

            List<Goal> result = useCase.execute();

            assertNotNull(result);
            assertTrue(result.isEmpty());
            assertEquals(0, result.size());
            verify(goalRepository, times(1)).findAll();
        }

        @Test
        void shouldReturnSameListInstanceFromRepository() {
            List<Goal> expectedList = Arrays.asList(new Goal(), new Goal());

            when(goalRepository.findAll()).thenReturn(expectedList);

            List<Goal> result = useCase.execute();

            assertSame(expectedList, result);
            verify(goalRepository, times(1)).findAll();
        }

        @Test
        void shouldCallRepositoryFindAllExactlyOnce() {
            when(goalRepository.findAll()).thenReturn(Collections.emptyList());

            useCase.execute();

            verify(goalRepository, times(1)).findAll();
            verifyNoMoreInteractions(goalRepository);
        }
    }

    @Nested
    class UpdateGoalUseCaseTests {
        private UpdateGoalUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new UpdateGoalUseCase(goalRepository);
        }

        @Test
        void shouldThrowExceptionWhenGoalIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null, null));

            assertEquals("Meta inválida", exception.getMessage());
            verify(goalRepository, never()).findById(any());
            verify(goalRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenIdIsNull() {
            Goal updated = new Goal();

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated.getId(), updated));

            assertEquals("Meta inválida", exception.getMessage());
            verify(goalRepository, never()).findById(any());
            verify(goalRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenGoalNotFound() {
            Integer goalId = 999;
            Goal updated = new Goal();
            updated.setId(goalId);

            when(goalRepository.findById(goalId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated.getId(), updated));

            assertEquals("Meta não encontrada", exception.getMessage());
            verify(goalRepository, times(1)).findById(goalId);
            verify(goalRepository, never()).save(any());
        }

        @Test
        void shouldValidateGoalBeforeCheckingExistence() {
            assertThrows(IllegalArgumentException.class, () -> useCase.execute(null, null));

            verify(goalRepository, never()).findById(any());
        }

        @Test
        void shouldCheckExistenceBeforeSaving() {
            Integer goalId = 1;
            Goal updated = new Goal();
            updated.setId(goalId);

            when(goalRepository.findById(goalId)).thenReturn(null);

            assertThrows(IllegalArgumentException.class, () -> useCase.execute(updated.getId(), updated));

            verify(goalRepository, times(1)).findById(goalId);
            verify(goalRepository, never()).save(any());
        }
    }
}