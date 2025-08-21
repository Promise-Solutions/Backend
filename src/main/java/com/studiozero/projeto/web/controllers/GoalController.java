package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.domain.entities.Goal;
import com.studiozero.projeto.web.dtos.request.GoalRequestDTO;
import com.studiozero.projeto.web.dtos.response.GoalResponseDTO;
import com.studiozero.projeto.web.mappers.GoalMapper;
import com.studiozero.projeto.application.usecases.goal.CreateGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.GetGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.ListGoalsUseCase;
import com.studiozero.projeto.application.usecases.goal.UpdateGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.DeleteGoalUseCase;
import com.studiozero.projeto.application.usecases.goal.GetMostRecentGoalUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
@RequiredArgsConstructor
public class GoalController {

    private final CreateGoalUseCase createGoalUseCase;
    private final GetGoalUseCase getGoalUseCase;
    private final ListGoalsUseCase listGoalsUseCase;
    private final UpdateGoalUseCase updateGoalUseCase;
    private final DeleteGoalUseCase deleteGoalUseCase;
    private final GetMostRecentGoalUseCase getMostRecentGoalUseCase;

    @PostMapping()
    public ResponseEntity<GoalResponseDTO> createGoal(@Valid @RequestBody GoalRequestDTO dto) {
        Goal goal = GoalMapper.toEntity(dto);
        createGoalUseCase.execute(goal);
        return ResponseEntity.ok(GoalMapper.toDTO(goal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalResponseDTO> getGoalById(@PathVariable Integer id) {
        Goal goal = getGoalUseCase.execute(id);
        return ResponseEntity.ok(GoalMapper.toDTO(goal));
    }

    @GetMapping
    public ResponseEntity<List<GoalResponseDTO>> listGoals() {
        List<Goal> goals = listGoalsUseCase.execute();
        List<GoalResponseDTO> goalsDtos = GoalMapper.toListDtos(goals);
        return ResponseEntity.ok(goalsDtos);
    }

    @GetMapping("/recent")
    public ResponseEntity<GoalResponseDTO> getMostRecentGoal() {
        Goal goal = getMostRecentGoalUseCase.execute();
        return ResponseEntity.ok(GoalMapper.toDTO(goal));
    }

    @PutMapping
    public ResponseEntity<GoalResponseDTO> updateGoal(@Valid @RequestBody Goal goal) {
        Goal updatedGoal = updateGoalUseCase.execute(goal);
        return ResponseEntity.ok(GoalMapper.toDTO(updatedGoal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Integer id) {
        deleteGoalUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
